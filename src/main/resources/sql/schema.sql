CREATE TABLE `store`
(
    `id`      BIGINT       NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(255) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE (`name`, `address`)
);

CREATE TABLE `menu`
(
    `id`       BIGINT       NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(255) NOT NULL,
    `price`    BIGINT       NOT NULL,
    `store_id` BIGINT       NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`store_id`) REFERENCES `store` (`id`),
    UNIQUE (`name`, `store_id`)
);

CREATE TABLE `account`
(
    `id`        BIGINT       NOT NULL AUTO_INCREMENT,
    `email`     VARCHAR(255) NOT NULL,
    `password`  VARCHAR(255) NOT NULL,
    `name`      VARCHAR(255),
    `authority` INTEGER DEFAULT 0,
    `address`   VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE (`email`)
);

CREATE TABLE `delivery`
(
    `id`           BIGINT NOT NULL AUTO_INCREMENT,
    `status`       INTEGER DEFAULT 0,
    `customer_id`  BIGINT NOT NULL,
    `ordered_date` TIMESTAMP,
    `store_id`     BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`customer_id`) REFERENCES `account` (`id`),
    FOREIGN KEY (`store_id`) REFERENCES `store` (`id`)
);

CREATE TABLE `delivery_detail`
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT,
    `count`       INTEGER DEFAULT 0,
    `delivery_id` BIGINT NOT NULL,
    `menu_id`     BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`delivery_id`) REFERENCES `delivery` (`id`),
    FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`)
);