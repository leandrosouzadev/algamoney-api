CREATE TABLE accounting_entry (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(50) NOT NULL,
	due_date DATE NOT NULL,
	payment_date DATE,
	value DECIMAL(10,2) NOT NULL,
	note VARCHAR(100),
	accounting_entry_type VARCHAR(20) NOT NULL,
	category_id BIGINT NOT NULL,
	person_id BIGINT NOT NULL,
	FOREIGN KEY (category_id) REFERENCES category(id),
	FOREIGN KEY (person_id) REFERENCES person(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO accounting_entry (description, due_date, payment_date, value, note, accounting_entry_type, category_id, person_id)
VALUES ('SALÁRIO MENSAL', '2020-01-10', null, 6500.00, 'DISTRIBUIÇÃO DE LUCROS', 'REVENUE', 1, 1),
	   ('VIAGEM AO NORDESTE', '2020-01-20', '2020-01-20', 2000.00, 'REUNIÃO COM A DIRETORIA', 'EXPENSE', 2, 4),
	   ('MENSALIDADE CLUBE', '2020-02-17', '2020-01-17', 100.00, 'CLUBE AGUA NOVA', 'EXPENSE', 3, 1),
	   ('COMPRA SUPERMERCADO', '2020-02-20', '2020-01-20', 200.00, 'SUPERMERCADO CENTRAL', 'EXPENSE', 4, 3);