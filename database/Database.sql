-- MySQL Script generated by MySQL Workbench
-- Sun Apr  7 18:53:11 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ChatopApp
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ChatopApp` ;

-- -----------------------------------------------------
-- Schema ChatopApp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ChatopApp` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ChatopApp` ;

-- -----------------------------------------------------
-- Table `ChatopApp`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ChatopApp`.`users` ;

CREATE TABLE IF NOT EXISTS `ChatopApp`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME(6) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `updated_at` DATETIME(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ChatopApp`.`rentals`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ChatopApp`.`rentals` ;

CREATE TABLE IF NOT EXISTS `ChatopApp`.`rentals` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME(6) NULL DEFAULT NULL,
  `description` VARCHAR(2000) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `picture` VARCHAR(255) NULL DEFAULT NULL,
  `price` FLOAT NOT NULL,
  `surface` FLOAT NOT NULL,
  `updated_at` DATETIME(6) NULL DEFAULT NULL,
  `owner_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKf462yhxa9vd3m2qdmcoixg1fv` (`owner_id` ASC) VISIBLE,
  CONSTRAINT `FKf462yhxa9vd3m2qdmcoixg1fv`
    FOREIGN KEY (`owner_id`)
    REFERENCES `ChatopApp`.`users` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ChatopApp`.`messages`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ChatopApp`.`messages` ;

CREATE TABLE IF NOT EXISTS `ChatopApp`.`messages` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME(6) NULL DEFAULT NULL,
  `message` VARCHAR(2000) NULL DEFAULT NULL,
  `updated_at` DATETIME(6) NULL DEFAULT NULL,
  `rental_id` INT NULL DEFAULT NULL,
  `owner_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK3ce1i9w1rtics9wjwj8y5y3md` (`rental_id` ASC) VISIBLE,
  INDEX `FKf5nm3ov34hjo0njg2k6rl4p7d` (`owner_id` ASC) VISIBLE,
  CONSTRAINT `FK3ce1i9w1rtics9wjwj8y5y3md`
    FOREIGN KEY (`rental_id`)
    REFERENCES `ChatopApp`.`rentals` (`id`),
  CONSTRAINT `FKf5nm3ov34hjo0njg2k6rl4p7d`
    FOREIGN KEY (`owner_id`)
    REFERENCES `ChatopApp`.`users` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
