package marlon.leoner.technical.assessment.aggregation;

import lombok.AllArgsConstructor;
import marlon.leoner.technical.assessment.domain.dto.MemberDTO;
import marlon.leoner.technical.assessment.domain.exception.ObjectAlreadyExistsException;
import marlon.leoner.technical.assessment.domain.exception.ObjectNotFoundException;
import marlon.leoner.technical.assessment.domain.model.Member;
import marlon.leoner.technical.assessment.domain.param.CreateMemberParam;
import marlon.leoner.technical.assessment.mapper.MemberMapper;
import marlon.leoner.technical.assessment.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MemberAggregation {

    private final MemberService service;

    public List<MemberDTO> getAllMembers() {
        return service.getAllMembers()
                .stream()
                .map(MemberMapper::toDTO)
                .toList();
    }

    public MemberDTO getMember(String memberId) throws ObjectNotFoundException {
        Member member = service.getMemberByIdOrException(memberId);
        return MemberMapper.toDTO(member);
    }

    public MemberDTO createMember(CreateMemberParam params) throws ObjectAlreadyExistsException {
        Optional<Member> other = service.getMemberByCpf(params.getCpf());
        if (other.isPresent()) throw new ObjectAlreadyExistsException(Member.class);

        Member member = service.createMember(params);
        return MemberMapper.toDTO(member);
    }
}
