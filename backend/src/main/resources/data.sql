INSERT INTO tb_ingredient (name, quantity) VALUES ('Açúcar', 5);
INSERT INTO tb_ingredient (name, quantity) VALUES ('Farinha de Trigo', 2);
INSERT INTO tb_ingredient (name, quantity) VALUES ('Farinha de Rosca', 2);
INSERT INTO tb_ingredient (name, quantity) VALUES ('Ovo', 7);
INSERT INTO tb_ingredient (name, quantity) VALUES ('Chocolate', 7);
INSERT INTO tb_ingredient (name, quantity) VALUES ('Morango', 7);

INSERT INTO tb_product (name, price, quantity) VALUES ('Bolo de chocolate', 50.00, 1);
INSERT INTO tb_product (name, price, quantity) VALUES ('Torta de morango', 25.00, 2);

INSERT INTO tb_product_ingredient (quantity, product_id, ingredient_id) VALUES (2, 1, 1);
INSERT INTO tb_product_ingredient (quantity, product_id, ingredient_id) VALUES (1, 1, 2);
INSERT INTO tb_product_ingredient (quantity, product_id, ingredient_id) VALUES (3, 1, 4);
INSERT INTO tb_product_ingredient (quantity, product_id, ingredient_id) VALUES (5, 1, 5);

INSERT INTO tb_product_ingredient (quantity, product_id, ingredient_id) VALUES (1, 2, 1);
INSERT INTO tb_product_ingredient (quantity, product_id, ingredient_id) VALUES (2, 2, 3);
INSERT INTO tb_product_ingredient (quantity, product_id, ingredient_id) VALUES (4, 2, 4);
INSERT INTO tb_product_ingredient (quantity, product_id, ingredient_id) VALUES (6, 2, 6);

INSERT INTO tb_order (moment, status) VALUES (TIMESTAMP WITH TIME ZONE '2022-04-05T08:30:00.12345Z', 0);
INSERT INTO tb_order (moment, status) VALUES (TIMESTAMP WITH TIME ZONE '2022-04-05T10:45:08.12345Z', 1);

INSERT INTO tb_order_product (order_id, product_id) VALUES (1, 1);
INSERT INTO tb_order_product (order_id, product_id) VALUES (2, 2);
