CREATE TABLE category ( 
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR (50) NOT NULL 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO category (name) values ('Recreation');
INSERT INTO category (name) values ('Food');
INSERT INTO category (name) values ('Supermarket');
INSERT INTO category (name) values ('Pharmacy');
INSERT INTO category (name) values ('Others');