ALTER TABLE users ADD password VARCHAR(20);
ALTER TABLE users
    ADD CONSTRAINT uk_user_email UNIQUE (email);