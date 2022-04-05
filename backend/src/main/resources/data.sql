INSERT INTO tb_ingredient (name, quantity_in_stock, unit_of_measure) VALUES ('Açúcar', 5, 'kg');
INSERT INTO tb_ingredient (name, quantity_in_stock, unit_of_measure) VALUES ('Farinha de Trigo', 2, 'kg');
INSERT INTO tb_ingredient (name, quantity_in_stock, unit_of_measure) VALUES ('Farinha de Rosca', 2, 'kg');
INSERT INTO tb_ingredient (name, quantity_in_stock, unit_of_measure) VALUES ('Ovo', 7, 'unidade');
INSERT INTO tb_ingredient (name, quantity_in_stock, unit_of_measure) VALUES ('Chocolate', 7, 'kg');
INSERT INTO tb_ingredient (name, quantity_in_stock, unit_of_measure) VALUES ('Morango', 7, 'unidade');

INSERT INTO tb_product (name, price) VALUES ('Bolo de chocolate', 50.00);
INSERT INTO tb_product (name, price) VALUES ('Torta de morango', 25.00);

INSERT INTO tb_product_ingredient (quantity, product_id, ingredient_id) VALUES (0.8, 1, 1);
INSERT INTO tb_product_ingredient (quantity, product_id, ingredient_id) VALUES (1.0, 1, 2);
INSERT INTO tb_product_ingredient (quantity, product_id, ingredient_id) VALUES (3.0, 1, 4);
INSERT INTO tb_product_ingredient (quantity, product_id, ingredient_id) VALUES (0.5, 1, 5);

INSERT INTO tb_product_ingredient (quantity, product_id, ingredient_id) VALUES (1, 2, 1);
INSERT INTO tb_product_ingredient (quantity, product_id, ingredient_id) VALUES (2, 2, 3);
INSERT INTO tb_product_ingredient (quantity, product_id, ingredient_id) VALUES (4, 2, 4);
INSERT INTO tb_product_ingredient (quantity, product_id, ingredient_id) VALUES (6, 2, 6);

INSERT INTO tb_order (moment, status) VALUES (TIMESTAMP WITH TIME ZONE '2022-04-05T08:30:00.12345Z', 0);
INSERT INTO tb_order (moment, status) VALUES (TIMESTAMP WITH TIME ZONE '2022-04-05T10:45:08.12345Z', 1);

INSERT INTO tb_order_product (quantity, order_id, product_id) VALUES (1, 1, 1);
INSERT INTO tb_order_product (quantity, order_id, product_id) VALUES (3, 2, 2);
