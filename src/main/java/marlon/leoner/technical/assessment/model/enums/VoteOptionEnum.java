package marlon.leoner.technical.assessment.model.enums;

import lombok.Getter;

@Getter
public enum VoteOptionEnum {

    YES("S", "Sim"),
    NO("N", "Não");

    private final String value;
    private final String description;

    VoteOptionEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static VoteOptionEnum getInstance(String vote) {
        for (VoteOptionEnum value : values()) {
            if (value.getValue().equals(vote)) {
                return value;
            }
        }

        return null;
    }
}
