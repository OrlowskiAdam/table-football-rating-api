CREATE TABLE ratings
(
    ra_id    UUID,
    ra_u_id  UUID,
    ra_p_id  UUID,
    ra_type  varchar(20),
    ra_value numeric(17, 6),
    ra_quarter  VARCHAR(2),
    ra_year     INTEGER
);

ALTER TABLE ratings
    ADD CONSTRAINT TFRC_RA_PK PRIMARY KEY (ra_id);

ALTER TABLE ratings
    ADD CONSTRAINT TFRC_RA_U_FK FOREIGN KEY (ra_u_id) REFERENCES "users" (u_id);
ALTER TABLE ratings
    ADD CONSTRAINT TFRC_RA_P_FK FOREIGN KEY (ra_p_id) REFERENCES pairs (p_id);

