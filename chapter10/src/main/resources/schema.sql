CREATE TABLE IF NOT EXISTS `token`
(
    `id`         BIGINT      NOT NULL AUTO_INCREMENT,
    `identifier` VARCHAR(45) NULL,
    `token`      TEXT        NULL,
    PRIMARY KEY (`id`)
);