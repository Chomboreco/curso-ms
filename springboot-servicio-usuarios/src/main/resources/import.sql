INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('Chomboreco', '12345', 1, 'Eduardo', 'Chombo', 'correo1@correo.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('Panchoman', '56789', 1, 'Pancho', 'Man', 'correo2@correo.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);