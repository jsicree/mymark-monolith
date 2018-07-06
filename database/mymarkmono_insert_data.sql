-- INSERT INTO greeting (simple_message, named_message, language) values ("Hello World!","Hello {name}!", "ENG");
-- INSERT INTO greeting (simple_message, named_message, language) values ("Hello World in Spanish!","Hello {name} in Spanish!", "SPA");
-- INSERT INTO greeting (simple_message, named_message, language) values ("Hello World in French!","Hello {name} in French!", "FRA");
-- 
-- COUNTRY ref data
INSERT INTO COUNTRY (code, name) values ("CA", "Canada");
INSERT INTO COUNTRY (code, name) values ("US", "United STATEs");
INSERT INTO COUNTRY (code, name) values ("UK", "United Kingdom");

-- STATE ref data
INSERT INTO STATE (name, code, COUNTRY_id) values ("Alabama","AL", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Alaska","AK", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Arizona","AZ", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Arkansas","AR", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("California","CA", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Colorado","CO", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Connecticut","CT", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Delaware","DE", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("District of Columbia","DC", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Florida","FL", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Georgia","GA", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Hawaii","HI", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Idaho","ID", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Illinois","IL", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Indiana","IN", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Iowa","IA", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Kansas","KS", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Kentucky","KY", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Louisiana","LA", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Maine","ME", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Maryland","MD", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Massachusetts","MA", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Michigan","MI", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Minnesota","MN", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Mississippi","MS", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Missouri","MO", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Montana","MT", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Nebraska","NE", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Nevada","NV", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("New Hampshire","NH", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("New Jersey","NJ", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("New Mexico","NM", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("New York","NY", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("North Carolina","NC", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("North Dakota","ND", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Ohio","OH", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Oklahoma","OK", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Oregon","OR", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Pennsylvania","PA", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Rhode Island","RI", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("South Carolina","SC", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("South Dakota","SD", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Tennessee","TN", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Texas","TX", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Utah","UT", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Vermont","VT", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Virginia","VA", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Washington","WA", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("West Virginia","WV", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Wisconsin","WI", (SELECT c.id from COUNTRY c where c.code="US"));
INSERT INTO STATE (name, code, COUNTRY_id) values ("Wyoming","WY", (SELECT c.id from COUNTRY c where c.code="US"));

-- Create Products
INSERT INTO PRODUCT (product_code, name, price, short_desc, long_desc) value ("PROD-001","Widget A", 19.99, "A simple widget", "Lorem ipsum ipso facto.");
INSERT INTO PRODUCT (product_code, name, price, short_desc, long_desc) value ("PROD-002","Widget B", 29.99, "An even better widget", "Lorem ipsum ipso facto.");

-- Create Inventory
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"AVAILABLE");

INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"SOLD");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"SOLD");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"SOLD");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"SOLD");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"SOLD");

INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"IN_PROCESS");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"IN_PROCESS");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"IN_PROCESS");

INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"RETURNED");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-001"),"RETURNED");

INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"AVAILABLE");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"AVAILABLE");

INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"SOLD");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"SOLD");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"SOLD");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"SOLD");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"SOLD");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"SOLD");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"SOLD");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"SOLD");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"SOLD");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"SOLD");

INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"IN_PROCESS");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"IN_PROCESS");
INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"IN_PROCESS");

INSERT INTO INVENTORY (product_id, status) values ((SELECT p.id from PRODUCT p where p.product_code = "PROD-002"),"RETURNED");
