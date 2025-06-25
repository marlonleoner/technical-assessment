package marlon.leoner.technical.assessment.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import marlon.leoner.technical.assessment.domain.model.Member;
import marlon.leoner.technical.assessment.domain.param.CreateMemberParam;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMapper {

    public static Member toEntity(CreateMemberParam params) {
        return new Member(params.getCpf(), params.getName());
    }
}
