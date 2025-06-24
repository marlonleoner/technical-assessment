package marlon.leoner.technical.assessment.service;

import lombok.AllArgsConstructor;
import marlon.leoner.technical.assessment.dto.CreateMemberRequest;
import marlon.leoner.technical.assessment.model.Member;
import marlon.leoner.technical.assessment.model.exception.ObjectNotFoundException;
import marlon.leoner.technical.assessment.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MemberService {

    private final MemberRepository repository;

    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    public Optional<Member> getMemberById(String memberId) {
        return repository.findById(memberId);
    }

    public Optional<Member> getMemberByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public Member getMemberByIdOrException(String memberId) throws ObjectNotFoundException {
        Optional<Member> member = this.getMemberById(memberId);
        return member.orElseThrow(() -> new ObjectNotFoundException(this.getClass()));
    }

    public Member createMember(CreateMemberRequest params) {
        Member member = params.toEntity();
        return repository.save(member);
    }

}
