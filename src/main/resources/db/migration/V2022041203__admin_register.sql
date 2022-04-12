INSERT INTO role VALUES (4, 'ADMIN', now());

INSERT INTO auth VALUES 
(nextval('auth_seq'), 'admin@medbay.com', '$2a$10$0fZU7QS/l6bL0BXUTbIDPu1TD0cMa2OKGCebNmIVv91I0wDkhm2AG', now());

INSERT INTO user_system VALUES (nextval('user_seq'), 1, NULL, 4, now());