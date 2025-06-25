package marlon.leoner.technical.assessment.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultEnum {
    APPROVED(1, "Aprovado"),
    REJECTED(2, "Rejeitado"),
    DRAW(3, "Empate");

    private Integer id;

    private String description;

    public static ResultEnum calculatedResult(int yesVotes, int noVotes) {
        if (yesVotes > noVotes) {
            return ResultEnum.APPROVED;
        } else if (noVotes > yesVotes) {
            return ResultEnum.REJECTED;
        }

        return ResultEnum.DRAW;
    }
}
