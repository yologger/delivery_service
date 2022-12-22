CREATE TABLE `store`
(
    `id`      BIGINT       NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(255) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `menu`
(
    `id`       BIGINT       NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(255) NOT NULL,
    `price`    BIGINT       NOT NULL,
    `store_id` BIGINT       NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`store_id`) REFERENCES `store` (`id`)
);

CREATE TABLE `account`
(
    `id`         BIGINT              NOT NULL AUTO_INCREMENT,
    `account_id` VARCHAR(255) UNIQUE NOT NULL,
    `password`   VARCHAR(255)        NOT NULL,
    `name`       VARCHAR(255),
    PRIMARY KEY (`id`),
    UNIQUE (`account_id`)
);