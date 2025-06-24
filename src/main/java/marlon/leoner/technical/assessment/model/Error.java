package marlon.leoner.technical.assessment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class Error {

    private Integer status;

    private String message;
}
