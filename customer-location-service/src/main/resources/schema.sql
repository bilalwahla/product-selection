DROP TABLE IF EXISTS customer_location;

CREATE TABLE customer_location (
  customer_id            VARCHAR(100) PRIMARY KEY NOT NULL,
  location               TEXT NOT NULL);

INSERT INTO customer_location (customer_id, location)
VALUES ('1a1d87d0e0c54f5bac2d384fd10d09ab', 'LONDON');
INSERT INTO customer_location (customer_id, location)
VALUES ('482a597b15f244fcae926de5ef30f231', 'LIVERPOOL');
