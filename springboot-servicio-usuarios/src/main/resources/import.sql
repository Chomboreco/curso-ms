INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('Chomboreco', '$2a$10$iveAxmyUR5a5h0Go4s5oMu3OkpD8tXbHhIHcO12QCOEYUCiHVlxwK', true, 'Eduardo', 'Chombo', 'correo1@correo.com');
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('Panchoman', '$2a$10$PJvvf814fZW0YIxWwpfpg.gItH40dWMhMK293E2AmSkRo6MLhkFTO', true, 'Pancho', 'Man', 'correo2@correo.com');

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 1);