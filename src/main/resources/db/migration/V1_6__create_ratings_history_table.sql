CREATE TABLE ratings_history
(
    rh_id                      UUID,
    rh_u_id                    UUID,
    rh_p_id                    UUID,
    rh_type                    varchar(20),
    rh_old_value               numeric(17,6),
    rh_new_value               numeric(17,6),
    rh_m_id                    UUID
);

ALTER TABLE ratings_history ADD CONSTRAINT TFRC_RH_PK PRIMARY KEY(rh_id);

ALTER TABLE ratings_history ADD CONSTRAINT TFRC_RH_U_FK FOREIGN KEY (rh_u_id) REFERENCES "users"(u_id);
ALTER TABLE ratings_history ADD CONSTRAINT TFRC_RH_P_FK FOREIGN KEY (rh_p_id) REFERENCES pairs(p_id);
ALTER TABLE ratings_history ADD CONSTRAINT TFRC_RH_M_FK FOREIGN KEY (rh_m_id) REFERENCES matches(m_id);

