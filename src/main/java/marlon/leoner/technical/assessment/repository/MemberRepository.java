package marlon.leoner.technical.assessment.repository;

import marlon.leoner.technical.assessment.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByCpf(String cpf);
}
