CREATE TABLE IF NOT EXISTS `product`
(
    `id`    BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name`  VARCHAR(45) NULL,
    `owner` VARCHAR(45) NULL
);
