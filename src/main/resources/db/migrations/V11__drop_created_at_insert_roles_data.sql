ALTER TABLE roles DROP COLUMN created_at;

INSERT INTO roles (role)
VALUES
    ('RaRa user'),
    ('User'),
    ('Admin'),
    ('Scientist');