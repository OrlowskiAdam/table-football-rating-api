package pl.adamorlowski.tablefootballratingapi.entity;

import lombok.Getter;

@Getter
public enum RatingType {
    ELO_RATING(RatingTypeNames.ELO_RATING),
    GOLDA_RATING(RatingTypeNames.GOLDA_RATING);

    public static class RatingTypeNames {

        public static final String ELO_RATING = "ELO_RATING";
        public static final String GOLDA_RATING = "GOLDA_RATING";
    }

    RatingType(String value) {
        this.value = value;
    }

    private final String value;

}
