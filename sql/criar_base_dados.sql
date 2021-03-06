-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SaqueNotas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema SaqueNotas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SaqueNotas` DEFAULT CHARACTER SET utf8 ;
USE `SaqueNotas` ;

-- -----------------------------------------------------
-- Table `SaqueNotas`.`Conta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SaqueNotas`.`Conta` (
  `idConta` INT NOT NULL AUTO_INCREMENT,
  `Saldo` DECIMAL(10,2) NULL,
  PRIMARY KEY (`idConta`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SaqueNotas`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SaqueNotas`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `CPF` CHAR(11) NOT NULL,
  `Nome` VARCHAR(32) NOT NULL,
  `IdConta` INT NOT NULL,
  PRIMARY KEY (`idCliente`, `IdConta`),
  UNIQUE INDEX `CPF_UNIQUE` (`CPF` ASC) VISIBLE,
  INDEX `fk_Cliente_Conta_idx` (`IdConta` ASC) VISIBLE,
  CONSTRAINT `fk_Cliente_Conta`
    FOREIGN KEY (`IdConta`)
    REFERENCES `SaqueNotas`.`Conta` (`idConta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SaqueNotas`.`CaixaEletronico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SaqueNotas`.`CaixaEletronico` (
  `idCaixa` INT NOT NULL AUTO_INCREMENT,
  `Notas` INT NULL,
  PRIMARY KEY (`idCaixa`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
