INSERT INTO market_table (market_id, market_name)
VALUES (1, 'E-commerce'),
       (2, 'ERP'),
       (3, 'Lojista que não deseja possuir e-commerce'),
       (4, 'Loja física'),
       (5, 'Telecom'),
       (6, 'Venda direta'),
       (7, 'Mobile First'),
       (8, 'Digital Onboarding');

INSERT INTO technology_table (technology_id, technology_name)
VALUES (1, 'Java 10'),
       (2, 'Kotlin'),
       (3, 'Kafka'),
       (4, 'Event Stream'),
       (5, 'Redis'),
       (6, 'MongoDB'),
       (7, 'NodeJS'),
       (8, 'Big Data Analytics'),
       (9, 'Hadoop'),
       (10, 'Pig'),
       (11, 'Cassandra');

INSERT INTO product_table (product_id, product_description, product_name)
VALUES (1, 'Ferramenta de integração para marketplaces', 'Gubee Integrador'),
       (2, 'Ferramenta para gestão e cálculo de fretes', 'Gubee Fretes'),
       (3, 'Ferramenta para gestão e cálculo de fretes', 'Gubee Fretes'),
       (4, 'Ferramenta especialistas em detecção e prevenção à fraude',
        'Gubee Antifraude');

INSERT INTO market_product (product_id, market_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 2),
       (2, 4),
       (3, 1),
       (3, 2),
       (3, 4),
       (4, 1),
       (4, 5),
       (4, 6),
       (4, 7),
       (4, 8);

INSERT INTO technology_product (product_id, technology_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (2, 6),
       (2, 7),
       (3, 6),
       (3, 7),
       (4, 3),
       (4, 8),
       (4, 9),
       (4, 10),
       (4, 11);
