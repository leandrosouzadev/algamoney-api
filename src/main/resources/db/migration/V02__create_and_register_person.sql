CREATE TABLE person(
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	address VARCHAR(100),
	number VARCHAR(10),
	complement VARCHAR(50),
	neighborhood VARCHAR(100),
	zip_code VARCHAR(8),
	city VARCHAR(50),
	state VARCHAR(2),
	active BIT NOT NULL
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO person (name, address, number, complement, neighborhood, zip_code, city, state, active)
VALUES ('LEANDRO SOUZA', 'AV JOAO DA SILVA', '201', 'CASA A', 'CENTRO', '20123000', 'PIRACICABA', 'SP', 1),
	   ('LEONARDO SOUZA', 'AV DA SAUDADE', '34', 'FUNDOS', 'CDHU', '11789002', 'JARINU', 'SP', 1),
	   ('JOAO FRANCISCO', 'RUA XV DE NOVEMBRO', '3', '', 'CENTRO', '22345098', 'JAÚ', 'SP', 0),
	   ('MARCELO DE MATOS', 'RUA FREDERICO MACEDO', '32', 'FUNDOS', 'CENTRO', '14098123', 'SÃO PAULO', 'SP', 1);