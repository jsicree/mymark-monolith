SET SQL_SAFE_UPDATES = 0;

delete from AUTHORITIES;
delete from USERS;
delete from ADDRESS; 
delete from CART_LINE_ITEM;
delete from SHOPPING_CART;
delete from CUSTOMER; 
delete from ACCOUNT; 
delete from INVENTORY;
delete from PRODUCT;
delete from STATE;
delete from COUNTRY;
delete from CREDENTIAL; 


alter table AUTHORITIES drop foreign key `USERS_FK`;
alter table ACCOUNT drop foreign key `BILLING_ADDRESS_FK`;
alter table ADDRESS drop foreign key `STATE_FK`;
alter table CUSTOMER drop foreign key `ACCOUNT_FK`;
alter table STATE drop foreign key `COUNTRY_FK`;

drop table ACCOUNT;
drop table ADDRESS;
drop table AUTHORITIES;
drop table COUNTRY;
drop table CREDENTIAL; 
drop table CUSTOMER;
drop table INVENTORY;
drop table PRODUCT;
drop table USERS;
drop table STATE;


SET SQL_SAFE_UPDATES = 1;


