package marlon.leoner.technical.assessment.repository;

import marlon.leoner.technical.assessment.model.Member;
import marlon.leoner.technical.assessment.model.Topic;
import marlon.leoner.technical.assessment.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, String> {

    Optional<Vote> findByMemberAndTopic(Member member, Topic topic);
}
