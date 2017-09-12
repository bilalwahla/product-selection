DROP TABLE IF EXISTS catalogue;

CREATE TABLE catalogue (
  id      VARCHAR(100) PRIMARY KEY NOT NULL,
  category          TEXT NOT NULL,
  product           TEXT NOT NULL,
  location          TEXT);


INSERT INTO catalogue (id, category,  product, location)
VALUES ('4384002ca4ae484e8abca8c879b95efb', 'Sports', 'Arsenal TV', 'LONDON');
INSERT INTO catalogue (id, category,  product, location)
VALUES ('bcf6b7fc4f1e47b1933d9ab35dda881e', 'SPorts', 'Chelsea TV', 'LONDON');
INSERT INTO catalogue (id, category,  product, location)
VALUES ('597ba3f0864d40ef91c40e0a4d70b5eb', 'Sports', 'Liverpool TV', 'LIVERPOOL');
INSERT INTO catalogue (id, category,  product, location)
VALUES ('e42ba5a3b8ba4548aa64ff510d936cad', 'News', 'Sky News', '');
INSERT INTO catalogue (id, category,  product, location)
VALUES ('ecc69b8837334d038000053c1b5c5027', 'News', 'Sky Sports News', '');
