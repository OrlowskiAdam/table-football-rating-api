CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO roles (id, name)
VALUES
    (UUID_GENERATE_V4(), 'ROLE_USER'),
    (UUID_GENERATE_V4(), 'ROLE_ADMIN');