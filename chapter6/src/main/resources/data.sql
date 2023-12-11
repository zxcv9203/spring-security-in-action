INSERT IGNORE INTO user(id, username, password, algorithm)
VALUES (1, 'kim', '$2a$12$DV8jZAv6pieHuGzObMO8j.MdSvTDOsRSQfLN3gVh3eAo1Pur0E0Uu', 'BCRYPT');

INSERT IGNORE INTO authority(id, name, user)
VALUES (1, 'READ', '1');

INSERT IGNORE INTO authority(id, name, user)
VALUES (2, 'WRITE', '1');

INSERT IGNORE INTO product(id, name, price, currency)
VALUES(1, 'Chocolate', '10', 'USD')