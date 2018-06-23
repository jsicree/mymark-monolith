-- drop table CUSTOMER;
-- drop table ACCOUNT;
-- drop table ADDRESS;
-- drop table STATE;
-- drop table COUNTRY;
-- 

CREATE TABLE `COUNTRY` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(80) NOT NULL,
  `CODE` varchar(3) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CODE_UNIQUE` (`CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `STATE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(80) NOT NULL,
  `CODE` varchar(3) NOT NULL,
  `COUNTRY_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `COUNTRY_FK_idx` (`COUNTRY_ID`),
  CONSTRAINT `COUNTRY_FK` FOREIGN KEY (`COUNTRY_ID`) REFERENCES `COUNTRY` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;


CREATE TABLE `ADDRESS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TYPE` varchar(20) NOT NULL,
  `PRIMARY_LINE` varchar(80) NOT NULL,
  `SECONDARY_LINE` varchar(80) DEFAULT NULL,
  `CITY` varchar(80) NOT NULL,
  `ZIP_CODE` varchar(9) NOT NULL,
  `STATE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `STATE_ID_idx` (`STATE_ID`),
  CONSTRAINT `STATE_FK` FOREIGN KEY (`STATE_ID`) REFERENCES `STATE` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ACCOUNT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `STATUS` varchar(20) NOT NULL,
  `BILLING_ADDRESS_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `BILLING_ADDRESS_FK_idx` (`BILLING_ADDRESS_ID`),
  CONSTRAINT `BILLING_ADDRESS_FK` FOREIGN KEY (`BILLING_ADDRESS_ID`) REFERENCES `ADDRESS` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

CREATE TABLE `CUSTOMER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(20) NOT NULL,
  `FIRST_NAME` varchar(45) NOT NULL,
  `LAST_NAME` varchar(45) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `ACCOUNT_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME_UNIQUE` (`USER_NAME`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`),
  KEY `ACCOUNT_FK_idx` (`ACCOUNT_ID`),
  CONSTRAINT `ACCOUNT_FK` FOREIGN KEY (`ACCOUNT_ID`) REFERENCES `ACCOUNT` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

CREATE TABLE `CREDENTIAL` (
  `CUSTOMER_ID` int(11) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  PRIMARY KEY (`CUSTOMER_ID`),
  CONSTRAINT `CUSTOMER_ID_PK` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `CUSTOMER` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
