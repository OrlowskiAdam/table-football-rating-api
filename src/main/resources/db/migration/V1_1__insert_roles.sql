INSERT INTO roles (id, name)
-- VALUES
--     (UUID_GENERATE_V4(), 'ROLE_USER'),
--     (UUID_GENERATE_V4(), 'ROLE_ADMIN');
VALUES
    (RANDOM_UUID(), 'ROLE_USER'),
    (RANDOM_UUID(), 'ROLE_ADMIN');