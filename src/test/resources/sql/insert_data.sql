-- Producers
INSERT INTO producers (name)
VALUES ('Samsung'),
       ('Apple');

-- Colors
INSERT INTO colors (name)
VALUES ('RED'),
       ('WHITE'),
       ('GREEN');

-- Models
INSERT INTO models (name, producer_id)
VALUES ('Galaxy S21', 1),
       ('Galaxy Note 20', 1),
       ('iPhone 13', 2),
       ('Galaxy A52', 1),
       ('iPhone SE', 2),
       ('Galaxy Z Fold 3', 1);

-- Phones
INSERT INTO phones (producer_id, model_id, price, year)
VALUES (1, 1, 999.99, 2022),
       (1, 2, 899.99, 2022),
       (2, 3, 1099.99, 2022),
       (2, 5, 799.99, 2020),
       (1, 6, 499.99, 2024),
       (1, 4, 599.99, 2021);

-- Phones_colors
INSERT INTO phones_colors (phone_id, color_id)
VALUES (1, 2),
       (2, 2),
       (3, 3),
       (4, 1),
       (5, 1),
       (5, 2),
       (5, 2),
       (6, 2),
       (6, 3);
