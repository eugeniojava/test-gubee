package com.eugeniojava.backend.controller;

import com.eugeniojava.backend.model.Product;
import com.eugeniojava.backend.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void givenListOfProducts_whenGetProducts_thenReturnJsonArray() throws Exception {
        //given
        var product1 = createProduct("Product 1", "Description 1");
        var product2 = createProduct("Product 2", "Description 2");
        given(productService.listAll()).willReturn(asList(product1, product2));

        //when
        mockMvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].productName", is(product1.getName())))
                .andExpect(jsonPath("$[1].productName", is(product2.getName())));

        //then
        Assertions.assertEquals(List.of(product1, product2), productService.listAll());
    }

    @Test
    public void givenEmptyListOfProducts_whenGetProducts_thenReturnEmptyJsonArray() throws Exception {
        //given
        given(productService.listAll()).willReturn(emptyList());

        //when
        mockMvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)))
                .andReturn();

        //then
        Assertions.assertEquals(emptyList(), productService.listAll());
    }

    private Product createProduct(String name, String description) {
        Product product = new Product();

        product.setName(name);
        product.setDescription(description);
        product.setMarkets(emptyList());
        product.setTechnologies(emptyList());

        return product;
    }
}
