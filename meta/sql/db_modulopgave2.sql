USE modulopgave2;

ALTER DATABASE modulopgave2 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# DROP TABLES
DROP TABLE IF EXISTS word_letter;
DROP TABLE IF EXISTS importedword;
DROP TABLE IF EXISTS word;
DROP TABLE IF EXISTS letter;

# CREATE TABLES
CREATE TABLE `importedword` (
  `Value` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `word` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Value` varchar(3) CHARACTER SET utf8 COLLATE utf8_danish_ci NOT NULL ,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Value` (`Value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `letter` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Value` varchar(10) CHARACTER SET utf8 COLLATE utf8_danish_ci NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Value` (`Value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `word_letter` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Word_Id` int(11) NOT NULL,
  `Letter_Id` int(11) NOT NULL,
  `Offset` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Word_Id` (`Word_Id`),
  KEY `Letter_Id` (`Letter_Id`),
  CONSTRAINT `FKWord_Lette356424` FOREIGN KEY (`Word_Id`) REFERENCES `word` (`Id`),
  CONSTRAINT `FKWord_Lette694330` FOREIGN KEY (`Letter_Id`) REFERENCES `letter` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


# INSERT letters into letter
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('a');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('b');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('c');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('d');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('e');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('f');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('g');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('h');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('i');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('j');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('k');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('l');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('m');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('n');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('o');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('p');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('q');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('r');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('s');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('t');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('u');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('v');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('w');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('x');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('y');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('z');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('æ');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('ø');
INSERT INTO `modulopgave2`.`letter` (`Value`) VALUES ('å');
