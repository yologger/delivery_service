CREATE TABLE `store`
(
    `id`      BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(255)       NOT NULL,
    `address` VARCHAR(255)       NOT NULL
);

CREATE TABLE `menu`
(
    `id`       BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(255)       NOT NULL,
    `price`    BIGINT             NOT NULL,
    `store_id` BIGINT             NOT NULL,
    FOREIGN KEY (`store_id`) REFERENCES `store` (`id`)
);
