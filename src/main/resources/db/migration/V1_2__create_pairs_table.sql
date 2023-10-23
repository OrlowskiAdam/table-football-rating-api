CREATE TABLE pairs
(
    p_id            UUID,
    p_u1_id         UUID,
    p_u2_id         UUID,
    p_name      VARCHAR NOT NULL
);

ALTER TABLE pairs ADD CONSTRAINT TFRC_P_PK PRIMARY KEY(p_id);
--todo napisać unique żeby nie było 2 taki samych par co mają tych samych userów
--ALTER TABLE pairs ADD CONSTRAINT TFRC_P_U1_U2_UQ UNIQUE (p_u1_id, p_u2_id);

ALTER TABLE pairs ADD CONSTRAINT TFRC_P_U1_FK FOREIGN KEY (p_u1_id) REFERENCES "users"(u_id);
ALTER TABLE pairs ADD CONSTRAINT TFRC_P_U2_FK FOREIGN KEY (p_u2_id) REFERENCES "users"(u_id);

