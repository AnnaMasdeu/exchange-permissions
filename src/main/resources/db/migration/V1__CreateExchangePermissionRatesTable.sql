CREATE TABLE rate
(
    exchange_id varchar(10)   NOT NULL,
    level       varchar(10)   NOT NULL,
    amount      numeric(5, 2) NOT NULL,
    currency    char(3)       NOT NULL,
    CONSTRAINT rate_pk PRIMARY KEY (exchange_id, level)
);

INSERT INTO rate (exchange_id, level, amount, currency)
VALUES ('NYSE', 'L1', 5, 'EUR');
INSERT INTO rate (exchange_id, level, amount, currency)
VALUES ('NYSE', 'L2', 10, 'EUR');
INSERT INTO rate (exchange_id, level, amount, currency)
VALUES ('LSE', 'L1', 7, 'EUR');
INSERT INTO rate (exchange_id, level, amount, currency)
VALUES ('LSE', 'L2', 9, 'EUR');