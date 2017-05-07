-- MySQL Script generated by MySQL Workbench
-- Sat May  6 02:49:18 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Book_Order
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Book_Order` ;

-- -----------------------------------------------------
-- Schema Book_Order
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Book_Order` DEFAULT CHARACTER SET utf8 ;
USE `Book_Order` ;

-- -----------------------------------------------------
-- Table `Book_Order`.`Category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Book_Order`.`Category` ;

CREATE TABLE IF NOT EXISTS `Book_Order`.`Category` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `type_UNIQUE` (`type` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Book_Order`.`Publisher`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Book_Order`.`Publisher` ;

CREATE TABLE IF NOT EXISTS `Book_Order`.`Publisher` (
  `Name` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(45) NULL,
  `Phone_Number` VARCHAR(45) NULL,
  PRIMARY KEY (`Name`),
  UNIQUE INDEX `Phone_Number_UNIQUE` (`Phone_Number` ASC),
  UNIQUE INDEX `Name_UNIQUE` (`Name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Book_Order`.`Book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Book_Order`.`Book` ;

CREATE TABLE IF NOT EXISTS `Book_Order`.`Book` (
  `ISBN` VARCHAR(40) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `Price` INT UNSIGNED NOT NULL,
  `Copies` INT NOT NULL, #########################not unsigned to operate the trigger
  `Publication_Year` DATE NOT NULL,
  `Category_ID` INT NOT NULL,
  `Publisher_Name` VARCHAR(45) NOT NULL,
  `Threshold` INT ZEROFILL NOT NULL,
  PRIMARY KEY (`ISBN`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC),
  INDEX `fk_Book_Category1_idx` (`Category_ID` ASC),
  INDEX `fk_Book_Publisher1_idx` (`Publisher_Name` ASC),
  CONSTRAINT `fk_Book_Category1`
    FOREIGN KEY (`Category_ID`)
    REFERENCES `Book_Order`.`Category` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Book_Publisher1`
    FOREIGN KEY (`Publisher_Name`)
    REFERENCES `Book_Order`.`Publisher` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Book_Order`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Book_Order`.`User` ;

CREATE TABLE IF NOT EXISTS `Book_Order`.`User` (
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Created` DATE NULL,
  `Manager` TINYINT NOT NULL,
  PRIMARY KEY (`Username`),
  UNIQUE INDEX `Username_UNIQUE` (`Username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Book_Order`.`Purchases`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Book_Order`.`Purchases` ;

CREATE TABLE IF NOT EXISTS `Book_Order`.`Purchases` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Quantity` INT NOT NULL,
  `Check_out` TINYINT NOT NULL DEFAULT 0,
  `Username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_Purchases_User1_idx` (`Username` ASC),
  CONSTRAINT `fk_Purchases_User1`
    FOREIGN KEY (`Username`)
    REFERENCES `Book_Order`.`User` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Book_Order`.`Authors`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Book_Order`.`Authors` ;

CREATE TABLE IF NOT EXISTS `Book_Order`.`Authors` (
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Book_Order`.`Book_has_Authors`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Book_Order`.`Book_has_Authors` ;

CREATE TABLE IF NOT EXISTS `Book_Order`.`Book_has_Authors` (
  `Book_ISBN` VARCHAR(40) NOT NULL,
  `Authors_Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Book_ISBN`, `Authors_Name`),
  INDEX `fk_Book_has_Authors_Authors1_idx` (`Authors_Name` ASC),
  INDEX `fk_Book_has_Authors_Book_idx` (`Book_ISBN` ASC),
  CONSTRAINT `fk_Book_has_Authors_Book`
    FOREIGN KEY (`Book_ISBN`)
    REFERENCES `Book_Order`.`Book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Book_has_Authors_Authors1`
    FOREIGN KEY (`Authors_Name`)
    REFERENCES `Book_Order`.`Authors` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Book_Order`.`Book_has_Purchases`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Book_Order`.`Book_has_Purchases` ;

CREATE TABLE IF NOT EXISTS `Book_Order`.`Book_has_Purchases` (
  `ISBN` VARCHAR(40) NOT NULL,
  `Purchases_ID` INT NOT NULL,
  PRIMARY KEY (`ISBN`, `Purchases_ID`),
  INDEX `fk_Book_has_Purchases_Purchases1_idx` (`Purchases_ID` ASC),
  INDEX `fk_Book_has_Purchases_Book1_idx` (`ISBN` ASC),
  CONSTRAINT `fk_Book_has_Purchases_Book1`
    FOREIGN KEY (`ISBN`)
    REFERENCES `Book_Order`.`Book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Book_has_Purchases_Purchases1`
    FOREIGN KEY (`Purchases_ID`)
    REFERENCES `Book_Order`.`Purchases` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Book_Order`.`Orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Book_Order`.`Orders` ;

CREATE TABLE IF NOT EXISTS `Book_Order`.`Orders` (
  `Book_ISBN` VARCHAR(40) NOT NULL,
  `Copies` INT NOT NULL,
  PRIMARY KEY (`Book_ISBN`),
  CONSTRAINT `fk_Order_Book1`
    FOREIGN KEY (`Book_ISBN`)
    REFERENCES `Book_Order`.`Book` (`ISBN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `Book_Order`;

DELIMITER $$

USE `Book_Order`$$
DROP TRIGGER IF EXISTS `Book_Order`.`Book_BEFORE_UPDATE` $$
USE `Book_Order`$$
CREATE DEFINER = CURRENT_USER TRIGGER `Book_Order`.`Book_BEFORE_UPDATE` BEFORE UPDATE ON `Book` FOR EACH ROW
BEGIN
	declare book_count int;
    set book_count = New.Copies;
    
    if book_count < 0 then
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Insufficient Number of Copies';
	end if;
END;$$


USE `Book_Order`$$
DROP TRIGGER IF EXISTS `Book_Order`.`Book_AFTER_UPDATE` $$
USE `Book_Order`$$
CREATE DEFINER = CURRENT_USER TRIGGER `Book_Order`.`Book_AFTER_UPDATE` AFTER UPDATE ON `Book` FOR EACH ROW
BEGIN
	declare book_count int;
    set book_count = New.Copies;
    
    if book_count < New.threshold then
		Insert into Orders values (New.ISBN, New.threshold);
	end if;
END;$$

USE `Book_Order`$$
DROP TRIGGER IF EXISTS `Book_Order`.`Order_BEFORE_DELETE` $$
USE `Book_Order`$$
CREATE DEFINER = CURRENT_USER TRIGGER `Book_Order`.`Order_BEFORE_DELETE` BEFORE DELETE ON `Orders` FOR EACH ROW
BEGIN
    declare copies_old int;
    select copies from book where book.ISBN = old.Book_ISBN into copies_old;
    update book set copies = copies_old + old.copies where book.ISBN = old.Book_ISBN;
END;$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

###############################SCHEMA TEST##################################
use `Book_Order`;
Insert into Category values (1, 'programming');
Insert into Publisher values ('Gayle lack macdwel', 'a', '89723894');
Insert into Book values ('1', 'Cracking the coding interview', 300, 7, date('2002-12-2'), 1, 'Gayle lack macdwel', 2);
Insert into Book values ('2', 'Introduction to Algorithms', 300, 5, date('2002-12-2'), 1, 'Gayle lack macdwel', 2);
Insert into Book values ('3', 'Competetive', 300, 4, date('2002-12-2'), 1, 'Gayle lack macdwel', 2);
Insert into Book values ('4', 'No Name', 300, 6, date('2002-12-2'), 1, 'Gayle lack macdwel', 2);
#Update Book set Book.Copies = -1 where Book.ISBN = '1';
Update Book set Book.Copies = 1 where ISBN = '4';
select * from Orders;
select Copies from Book where ISBN = '4';
#select * from Orders where Book_ISBN = '4';
delete from Orders where Book_ISBN = '4';