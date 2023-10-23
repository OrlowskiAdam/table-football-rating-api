CREATE TABLE users
(
    id            UUID PRIMARY KEY,
    nickname      VARCHAR NOT NULL,
    name          VARCHAR NOT NULL,
    username      VARCHAR NOT NULL,
    password      VARCHAR NOT NULL,
    CONSTRAINT user_username_unique UNIQUE (username),
    CONSTRAINT user_nickname_unique UNIQUE (nickname)
);

CREATE INDEX idx_user_username ON users (username);
CREATE INDEX idx_user_nickname ON users (nickname);

CREATE TABLE roles
(
    id   UUID PRIMARY KEY,
    name VARCHAR(60) NOT NULL
);

CREATE INDEX idx_role_name ON roles (name);

CREATE TABLE user_roles
(
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    PRIMARY KEY (user_id, role_id)
);
