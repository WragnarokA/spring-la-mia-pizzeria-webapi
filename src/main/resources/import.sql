INSERT INTO db_pizzeria.pizze (name, description, prezzo, url) VALUES('Margherita', 'Pomodoro, Mozzarella, Mozzarella', 10.99, 'https://tinyurl.com/3nsyc27f');
INSERT INTO db_pizzeria.pizze (name, description, prezzo, url) VALUES('Pepperoni', 'Salame piccante, Formaggio',  11.99, 'https://tinyurl.com/kajtm5nf');
INSERT INTO db_pizzeria.pizze (name, description, prezzo, url) VALUES('Quattro Stagioni', 'Prosciutto, Funghi, Carciofi, Olive',  12.99, 'https://tinyurl.com/3p95dm37');
INSERT INTO db_pizzeria.pizze (name, description, prezzo, url) VALUES('Capricciosa', 'Prosciutto, funghi, carciofi, olive', 13.99, 'https://tinyurl.com/bdcfpybn');
INSERT INTO db_pizzeria.pizze (name, description, prezzo, url) VALUES('Quattro Formaggi', 'Mozzarella, gorgonzola, fontina, parmigiano', 14.99, 'https://tinyurl.com/28unz4u9');
INSERT INTO db_pizzeria.pizze (name, description, prezzo, url) VALUES('Vegetariana', 'Peperoni, Cipolle, Olive, pomodori', 10.99, 'https://tinyurl.com/5dhpwzrz');
INSERT INTO db_pizzeria.pizze (name, description, prezzo, url) VALUES('Hawaiiana', 'Prosciutto, Ananas', 12.99, 'https://tinyurl.com/3hvktrhv');
INSERT INTO db_pizzeria.pizze (name, description, prezzo, url) VALUES('Diavola', 'Salame piccante, Peperoncino', 12.99, 'https://tinyurl.com/2jyz2c6e');
INSERT INTO db_pizzeria.pizze (name, description, prezzo, url) VALUES('Bufala', 'Mozzarella di bufala, Pomodoro', 15.99, 'https://tinyurl.com/4xy4yfcy');
INSERT INTO db_pizzeria.pizze (name, description, prezzo, url) VALUES('Tonno e Cipolla', 'Tonno, cipolla', 11.99, 'https://tinyurl.com/yr2vfa7e');

INSERT INTO db_pizzeria.ingredients (name) VALUES("Pomodoro");
INSERT INTO db_pizzeria.ingredients (name) VALUES("Mozzarella");
INSERT INTO db_pizzeria.ingredients (name) VALUES("Salame piccante");
INSERT INTO db_pizzeria.ingredients (name) VALUES("Salsiccia");
INSERT INTO db_pizzeria.ingredients (name) VALUES("Pancetta");
INSERT INTO db_pizzeria.ingredients (name) VALUES("Prosciutto Cotto");
INSERT INTO db_pizzeria.ingredients (name) VALUES("Prosciutto");
INSERT INTO db_pizzeria.ingredients (name) VALUES("Funghi");
INSERT INTO db_pizzeria.ingredients (name) VALUES("Funghi Porcini");
INSERT INTO db_pizzeria.ingredients (name) VALUES("Cipolle");
INSERT INTO db_pizzeria.ingredients (name) VALUES("Olive");
INSERT INTO db_pizzeria.ingredients (name) VALUES("Mozzarella di bufala");
INSERT INTO db_pizzeria.ingredients (name) VALUES("Peperoncino");
INSERT INTO db_pizzeria.ingredients (name) VALUES("Tonno");
INSERT INTO db_pizzeria.ingredients (name) VALUES("Ananas");

INSERT INTO pizze_ingredients (pizza_id, ingredients_id) VALUES(1, 1);

INSERT INTO discounts (id, expire_date, start_date, title, pizza_id) VALUES(1, '15/12/2023', '24/11/2023', '20 Percento', 1);

INSERT INTO db_pizzeria.roles(id, name) VALUES(1, 'ADMIN');
INSERT INTO db_pizzeria.roles(id, name) VALUES(2, 'USER');

INSERT INTO `user` (email, first_name, last_name, registered_ad, password) VALUES('will@gmail.com', 'Will', 'Smith', '2023-12-15', '{noop}will');
INSERT INTO `user` (email, first_name, last_name, registered_ad, password) VALUES('maria@gmail.com', 'Maira', 'Dibi', '2023-12-25', '{noop}maria');

INSERT INTO user_roles (user_id, roles_id) VALUES(1, 1);
INSERT INTO user_roles (user_id, roles_id) VALUES(1, 2);
INSERT INTO user_roles (user_id, roles_id) VALUES(2, 2);
