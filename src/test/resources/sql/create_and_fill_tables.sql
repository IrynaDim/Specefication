create table category
(
    id   serial not null,
    type varchar(50),
    primary key (id)
);

create table producer
(
    id   serial not null,
    name varchar(30),
    primary key (id)
);

create table car_model
(
    id          serial not null,
    producer_id integer,
    model       varchar(50),
    primary key (id)
);

create table car
(
    object_id    varchar(10) not null,
    producer_id  integer references producer (id) on delete cascade,
    car_model_id integer references car_model (id) on delete cascade,
    category_id  integer references category (id) on delete cascade,
    model_year   smallint,
    primary key (object_id)
);

INSERT INTO category (type)
VALUES ('SUV'),
       ('Sedan'),
       ('Coupe');

INSERT INTO producer (name)
VALUES ('Audi'),
       ('Chevrolet'),
       ('Cadillac');

INSERT INTO car_model (producer_id, model)
VALUES (1, 'Q3'),
       (2, 'Malibu'),
       (3, 'Escalade ESV');

INSERT INTO car (object_id, producer_id, car_model_id, category_id, model_year)
VALUES ('veP6afpAx8', 1, 1, 1, 2020),
       ('zr5E0emp3X', 2, 2, 2, 2020);
