package marlon.leoner.technical.assessment.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import marlon.leoner.technical.assessment.domain.dto.MemberDTO;
import marlon.leoner.technical.assessment.domain.model.Member;
import marlon.leoner.technical.assessment.domain.param.CreateMemberParam;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMapper {

    public static Member toEntity(CreateMemberParam params) {
        return new Member(params.getCpf(), params.getName());
    }

    public static MemberDTO toDTO(Member member) {
        MemberDTO dto = new MemberDTO();
        dto.setId(member.getId());
        dto.setCpf(member.getCpf());
        dto.setName(member.getName());

        return dto;
    }
}
