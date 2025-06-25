package marlon.leoner.technical.assessment.service;

import marlon.leoner.technical.assessment.domain.enums.VoteOptionEnum;
import marlon.leoner.technical.assessment.domain.model.Member;
import marlon.leoner.technical.assessment.domain.model.Session;
import marlon.leoner.technical.assessment.domain.model.Vote;
import marlon.leoner.technical.assessment.repository.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoteServiceTest {

    @Mock
    private VoteRepository repository;

    @InjectMocks
    private VoteService voteService;

    private Session session;
    private Member member;

    @BeforeEach
    void setup() {
        session = new Session();

        member = new Member();
    }

    private Vote generateVote(VoteOptionEnum value) {
        return new Vote(session, member, value);
    }

    @Test
    void shouldSaveVoteSuccessfully() {
        Vote vote = generateVote(VoteOptionEnum.YES);

        when(repository.save(any(Vote.class))).thenReturn(vote);

        Vote savedVote = voteService.createVote(session, member, VoteOptionEnum.YES);

        assertNotNull(savedVote);
        assertEquals(VoteOptionEnum.YES, savedVote.getValue());
        verify(repository).save(any(Vote.class));
    }

    @Test
    void shouldReturnMemberVoteIfExists() {
        Vote vote = generateVote(VoteOptionEnum.NO);

        when(repository.findBySessionAndMember(session, member)).thenReturn(Optional.of(vote));

        Optional<Vote> result = voteService.getMemberVoteInSession(session, member);

        assertTrue(result.isPresent());
        assertEquals(VoteOptionEnum.NO, result.get().getValue());
    }

    @Test
    void shouldReturnEmptyIfMemberHasNotVoted() {
        when(repository.findBySessionAndMember(session, member)).thenReturn(Optional.empty());

        Optional<Vote> result = voteService.getMemberVoteInSession(session, member);

        assertTrue(result.isEmpty());
    }
}
