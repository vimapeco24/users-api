CREATE TABLE IF NOT EXISTS `person` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `person_bootcamp` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `person_id` BIGINT NOT NULL,
    `bootcamp_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`));