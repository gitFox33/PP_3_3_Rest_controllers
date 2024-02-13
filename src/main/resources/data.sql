INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT INTO users (username, password, age, name, email) VALUES ('admin', 'admin', 24, 'Alex', 'Alex@mail.ru');
INSERT INTO users (username, password, age, name, email) VALUES ('user', 'user', 14, 'Oleg', 'Oleg@mail.com');

INSERT INTO users_roles (user_id, roles_id) VALUES
    ((SELECT id FROM users WHERE username = 'admin'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')),
    ((SELECT id FROM users WHERE username = 'user'), (SELECT id FROM roles WHERE name = 'ROLE_USER'));
