CREATE TABLE PUBLIC.users
(
    user_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    role_id INTEGER,
    created_at TIMESTAMP,
    FOREIGN KEY(role_id) REFERENCES roles(role_id)
);