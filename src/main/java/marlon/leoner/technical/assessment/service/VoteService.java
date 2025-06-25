package marlon.leoner.technical.assessment.service;

import lombok.RequiredArgsConstructor;
import marlon.leoner.technical.assessment.domain.enums.VoteOptionEnum;
import marlon.leoner.technical.assessment.domain.model.Member;
import marlon.leoner.technical.assessment.domain.model.Session;
import marlon.leoner.technical.assessment.domain.model.Vote;
import marlon.leoner.technical.assessment.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VoteService {

    private static final String USER_ALREADY_VOTE = "O associado informado já votou nesta sessão.";

    private final VoteRepository repository;

    public Vote createVote(Session session, Member member, VoteOptionEnum value) {
        Vote vote = new Vote(session, member, value);
        return repository.save(vote);
    }

    public Optional<Vote> getMemberVoteInSession(Session session, Member member) {
        return repository.findBySessionAndMember(session, member);
    }
}
