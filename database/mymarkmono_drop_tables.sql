SET SQL_SAFE_UPDATES = 0;

delete from AUTHORITIES;
delete from USERS;
delete from SHOPPING_CART;
delete from CUSTOMER; 
delete from ACCOUNT; 
delete from INVENTORY;
delete from PRODUCT;
delete from ADDRESS; 
delete from STATE;
delete from COUNTRY;


-- alter table AUTHORITIES drop foreign key `USERS_FK`;
alter table ACCOUNT drop foreign key `BILLING_ADDRESS_FK`;
alter table ACCOUNT drop foreign key `ACCOUNT_CUST_FK`;
alter table ADDRESS drop foreign key `STATE_FK`;
alter table CUSTOMER drop foreign key `ACCOUNT_FK`;
alter table CUSTOMER drop foreign key `CUST_CART_FK`;
alter table STATE drop foreign key `COUNTRY_FK`;
alter table SHOPPING_CART drop foreign key `CUSTOMER_FK`;
alter table INVENTORY drop foreign key `PRODUCT_ID_FK`;
alter table CART_LINE_ITEM drop foreign key `CART_FK`;
alter table CART_LINE_ITEM drop foreign key `PRODUCT_FK`;

drop table SHOPPING_CART;
drop table CART_LINE_ITEM;
drop table ACCOUNT;
drop table ADDRESS;
drop table AUTHORITIES;
drop table COUNTRY;
drop table CUSTOMER;
drop table INVENTORY;
drop table PRODUCT;
drop table USERS;
drop table STATE;


SET SQL_SAFE_UPDATES = 1;


