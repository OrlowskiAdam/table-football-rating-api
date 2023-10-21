CREATE TABLE pairs
(
    id UUID PRIMARY KEY,
);

CREATE INDEX idx_pair_id ON pairs (id);

CREATE TABLE pair_users
(
    pair_id UUID NOT NULL,
    user_id UUID NOT NULL,
    PRIMARY KEY (pair_id, user_id)
);

CREATE INDEX idx_pair_user_pair_id ON pair_users (pair_id);
CREATE INDEX idx_pair_user_user_id ON pair_users (user_id);

CREATE TABLE pair_ratings
(
    pair_id UUID NOT NULL,
    rating_id UUID NOT NULL,
    PRIMARY KEY (pair_id, rating_id)
);

CREATE INDEX idx_pair_rating_pair_id ON pair_ratings (pair_id);

CREATE TABLE pair_matches
(
    pair_id UUID NOT NULL,
    match_id UUID NOT NULL,
    PRIMARY KEY (pair_id, match_id)
);