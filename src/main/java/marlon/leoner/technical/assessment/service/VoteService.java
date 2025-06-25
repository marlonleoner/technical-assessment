package marlon.leoner.technical.assessment.service;

import lombok.RequiredArgsConstructor;
import marlon.leoner.technical.assessment.domain.model.Member;
import marlon.leoner.technical.assessment.domain.model.Topic;
import marlon.leoner.technical.assessment.domain.model.Vote;
import marlon.leoner.technical.assessment.domain.enums.VoteOptionEnum;
import marlon.leoner.technical.assessment.domain.exception.ObjectAlreadyExistsException;
import marlon.leoner.technical.assessment.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VoteService {

    private static final String USER_ALREADY_VOTE = "O associado informado já votou nesta sessão.";

    private final VoteRepository repository;

    public Vote createVote(Topic topic, Member member, VoteOptionEnum value) {
        Vote vote = new Vote(topic, member, value);
        return repository.save(vote);
    }

    public Optional<Vote> getMemberVoteInTopic(Member member, Topic topic) {
        return repository.findByMemberAndTopic(member, topic);
    }

    public void validateMemberVoteInTopic(Member member, Topic topic) throws ObjectAlreadyExistsException {
        Optional<Vote> vote = getMemberVoteInTopic(member, topic);
        if (vote.isPresent()) {
            throw new ObjectAlreadyExistsException(USER_ALREADY_VOTE);
        }
    }
}
