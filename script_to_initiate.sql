INSERT INTO roles(role_id, name) VALUES (1,'ADMIN');
INSERT INTO roles(role_id, name) VALUES (2,'USER');
INSERT INTO users(user_id, created_at, email, full_name, password, phone_number, updated_at, username)
VALUES (1,timestamp(current_date),'admin@gmail.com','admin','$2a$10$RPXuViaS.jLiZfLTWroySO8s7qoBQGieWstJCcy9odwTSaPpe4svy',050,timestamp(current_date),'admin');
INSERT INTO users_roles(user_id, role_id) VALUES (1,1);
