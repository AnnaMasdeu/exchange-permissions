CREATE TABLE rate
(
    id          int       NOT NULL IDENTITY(1, 1),
    exchange_id char(128) NOT NULL,
    level       char(128) NOT NULL,
    amount      int       NOT NULL,
    currency    char(3)   NOT NULL,
    CONSTRAINT rate_pk PRIMARY KEY (id)
);

INSERT INTO rate (exchange_id, level, amount, currency)
VALUES ('NYSE', 'L1', 5, 'EUR');
INSERT INTO rate (exchange_id, level, amount, currency)
VALUES ('NYSE', 'L2', 10, 'EUR');
INSERT INTO rate (exchange_id, level, amount, currency)
VALUES ('LSE', 'L1', 7, 'EUR');
INSERT INTO rate (exchange_id, level, amount, currency)
VALUES ('LSE', 'L2', 9, 'EUR');