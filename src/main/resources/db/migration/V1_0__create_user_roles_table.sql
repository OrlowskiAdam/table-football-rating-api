CREATE TABLE "users"
(
    u_id            UUID,
    u_nickname      VARCHAR NOT NULL,
    u_name          VARCHAR NOT NULL,
    u_username      VARCHAR NOT NULL,
    u_password      VARCHAR NOT NULL
);

ALTER TABLE "users" ADD CONSTRAINT TFRC_U_PK PRIMARY KEY(u_id);
ALTER TABLE "users" ADD CONSTRAINT TFRC_U_USERNAME_UQ UNIQUE (u_username);
ALTER TABLE "users" ADD CONSTRAINT TFRC_U_NICKNAME_UQ UNIQUE (u_nickname);


CREATE TABLE roles
(
    r_id UUID,
    r_name VARCHAR(60) NOT NULL
);

ALTER TABLE roles ADD CONSTRAINT TFRC_R_PK PRIMARY KEY(r_id);
CREATE INDEX idx_r_name ON roles (r_name);

CREATE TABLE user_roles
(
    ur_u_id UUID NOT NULL,
    ur_r_id UUID NOT NULL
);

ALTER TABLE user_roles ADD CONSTRAINT TFRC_UR_PK PRIMARY KEY(ur_u_id, ur_r_id);
