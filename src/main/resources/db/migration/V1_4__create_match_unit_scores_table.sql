CREATE TABLE match_unit_scores
(
    mus_id                      UUID,
    mus_m_id                    UUID,
    mus_p1_score                INT,
    mus_p2_score                INT,
    mus_set_number              INT
);

ALTER TABLE match_unit_scores ADD CONSTRAINT TFRC_MUS_PK PRIMARY KEY(mus_id);

ALTER TABLE match_unit_scores ADD CONSTRAINT TFRC_MUS_M_FK FOREIGN KEY (mus_m_id) REFERENCES matches(m_id);

