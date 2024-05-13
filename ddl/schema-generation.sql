BEGIN;

DROP TABLE IF EXISTS cars;
CREATE TABLE IF NOT EXISTS cars
(
    id      bigint AUTO_INCREMENT PRIMARY KEY,
    model   text,
    series  int
);

DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users
(
    id        bigint AUTO_INCREMENT PRIMARY KEY,
    name      text,
    last_name text,
    email     text,
    car_id bigint UNIQUE,
    FOREIGN KEY (car_id) REFERENCES users (id)
);

COMMIT;
