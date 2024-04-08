DROP TABLE IF EXISTS phones;
DROP TABLE IF EXISTS models;
DROP TABLE IF EXISTS producers;
DROP TABLE IF EXISTS colors;

CREATE TABLE producers (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL
);

CREATE TABLE colors (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL
);

CREATE TABLE models (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        producer_id BIGINT NOT NULL,
                        FOREIGN KEY (producer_id) REFERENCES producers(id)
);

CREATE TABLE phones (
                        id SERIAL PRIMARY KEY,
                        producer_id INT NOT NULL,
                        model_id INT NOT NULL,
                        price DOUBLE PRECISION NOT NULL,
                        year INT NOT NULL,
                        FOREIGN KEY (producer_id) REFERENCES producers(id),
                        FOREIGN KEY (model_id) REFERENCES models(id)
);

CREATE TABLE phones_colors (
                        phone_id BIGINT NOT NULL,
                        color_id BIGINT NOT NULL,
                        FOREIGN KEY (phone_id) REFERENCES phones(id),
                        FOREIGN KEY (color_id) REFERENCES colors(id)
);
