package com.eugeniojava.backend.controller;

import com.eugeniojava.backend.model.Product;
import com.eugeniojava.backend.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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
    private MockMvc mvc;

    @MockBean
    private ProductService service;

    @Test
    public void givenProducts_whenGetProducts_thenReturnJsonArray() throws Exception {
        // given
        var product1 =
                createProduct("Gubee Fretes", "Ferramenta para gerenciamento de fretes");
        var product2 =
                createProduct("Gubee Integrador", "Ferramenta para gerenciamento de marketplaces");
        given(service.listAll()).willReturn(asList(product1, product2));

        // when
        mvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].productName", is(product1.getName())))
                .andExpect(jsonPath("$[1].productName", is(product2.getName())));

        // then
        verify(service, VerificationModeFactory.times(1)).listAll();
        reset(service);
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
