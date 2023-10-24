CREATE TABLE matches
(
    m_id                        UUID,
    m_p1_id                     UUID,
    m_p2_id                     UUID,
    m_match_date                TIMESTAMP not null,
    m_p1_score                  INT,
    m_p2_score                  INT
);

ALTER TABLE matches ADD CONSTRAINT TFRC_M_PK PRIMARY KEY(m_id);

ALTER TABLE matches ADD CONSTRAINT TFRC_M_P1_FK FOREIGN KEY (m_p1_id) REFERENCES pairs(p_id);
ALTER TABLE matches ADD CONSTRAINT TFRC_M_P2_FK FOREIGN KEY (m_p2_id) REFERENCES pairs(p_id);

