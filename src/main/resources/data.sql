INSERT INTO 
    employees (firstname, lastname, email) 
VALUES
    ('Lokesh', 'Sharma', 'loki@gmail.com'),
    ('Apurv', 'Goel', 'apurv@gmail.com'),
    ('Neha', 'Dhupia', 'neha@gmail.com');
    
    
INSERT INTO 
    users (username, password) 
VALUES
('admin', '$2a$12$Cj502QfCatxQcreSEZXz5ODustiMVHjQlwt.K5thViFnKmxlsxA0q'),
('user1', '$2a$12$3sQBZYxZGmMhYE7Utx40VuRWb5hoiU0FrOnBEX/vsC5xBmJy0yQNG');


INSERT INTO 
    roles (name) 
VALUES
('ADMIN'),
('USER');


INSERT INTO 
    users_roles (user_id, role_id) 
VALUES
(1, 1),
(2, 2);

