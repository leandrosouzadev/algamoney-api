CREATE TABLE user (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permission (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_permission (
	user_id BIGINT NOT NULL,
	permission_id BIGINT NOT NULL,
	PRIMARY KEY (user_id, permission_id),
	FOREIGN KEY (user_id) REFERENCES user(id),
	FOREIGN KEY (permission_id) REFERENCES permission(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user (id, name, email, password) values (1, 'Administrador', 'admin@algamoney.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO user (id, name, email, password) values (2, 'Maria Silva', 'maria@algamoney.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO permission (id, description) values (1, 'ROLE_REGISTER_CATEGORY');
INSERT INTO permission (id, description) values (2, 'ROLE_SEARCH_CATEGORY');

INSERT INTO permission (id, description) values (3, 'ROLE_REGISTER_PERSON');
INSERT INTO permission (id, description) values (4, 'ROLE_REMOVE_PERSON');
INSERT INTO permission (id, description) values (5, 'ROLE_SEARCH_PERSON');

INSERT INTO permission (id, description) values (6, 'ROLE_REGISTER_ACCOUNTING_ENTRY');
INSERT INTO permission (id, description) values (7, 'ROLE_REMOVE_ACCOUNTING_ENTRY');
INSERT INTO permission (id, description) values (8, 'ROLE_SEARCH_ACCOUNTING_ENTRY');

-- admin
INSERT INTO user_permission (user_id, permission_id) values (1, 1);
INSERT INTO user_permission (user_id, permission_id) values (1, 2);
INSERT INTO user_permission (user_id, permission_id) values (1, 3);
INSERT INTO user_permission (user_id, permission_id) values (1, 4);
INSERT INTO user_permission (user_id, permission_id) values (1, 5);
INSERT INTO user_permission (user_id, permission_id) values (1, 6);
INSERT INTO user_permission (user_id, permission_id) values (1, 7);
INSERT INTO user_permission (user_id, permission_id) values (1, 8);

-- maria
INSERT INTO user_permission (user_id, permission_id) values (2, 2);
INSERT INTO user_permission (user_id, permission_id) values (2, 5);
INSERT INTO user_permission (user_id, permission_id) values (2, 8);