INSERT IGNORE INTO `users`(`id`, `username`, `password`, `algorithm`)
VALUES (1, 'kim', '$2a$12$DV8jZAv6pieHuGzObMO8j.MdSvTDOsRSQfLN3gVh3eAo1Pur0E0Uu', 'BCRYPT');

INSERT IGNORE INTO `authorities`(`id`, `name`, `user_id`)
VALUES (1, 'READ', '1');

INSERT IGNORE INTO `authorities`(`id`, `name`, `user_id`)
VALUES (2, 'WRITE', '1');

INSERT IGNORE INTO `products`(`id`, `name`, `price`, `currency`)
VALUES(1, 'Chocolate', '10', 'USD')