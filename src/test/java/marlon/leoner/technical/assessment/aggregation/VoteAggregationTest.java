package marlon.leoner.technical.assessment.aggregation;

import marlon.leoner.technical.assessment.domain.enums.VoteOptionEnum;
import marlon.leoner.technical.assessment.domain.exception.MemberNotAllowedException;
import marlon.leoner.technical.assessment.domain.exception.SessionClosedException;
import marlon.leoner.technical.assessment.domain.exception.VoteAlreadyExistsException;
import marlon.leoner.technical.assessment.domain.model.Member;
import marlon.leoner.technical.assessment.domain.model.Session;
import marlon.leoner.technical.assessment.domain.model.Vote;
import marlon.leoner.technical.assessment.domain.param.CreateVoteParam;
import marlon.leoner.technical.assessment.service.IntegrationService;
import marlon.leoner.technical.assessment.service.MemberService;
import marlon.leoner.technical.assessment.service.SessionService;
import marlon.leoner.technical.assessment.service.VoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VoteAggregationTest {

    @Mock
    private SessionService sessionService;

    @Mock
    private MemberService memberService;

    @Mock
    private VoteService voteService;

    @Mock
    private IntegrationService integrationService;

    @InjectMocks
    private VoteAggregation aggregation;

    private Session session;
    private Member member;
    private CreateVoteParam param;

    @BeforeEach
    void setup() throws NoSuchFieldException, IllegalAccessException {
        Vote vote = new Vote();
        Field value = Vote.class.getDeclaredField("value");
        value.setAccessible(true);
        value.set(vote, VoteOptionEnum.YES);

        session = mock(Session.class);

        Field startedAt = Session.class.getDeclaredField("startedAt");
        startedAt.setAccessible(true);
        startedAt.set(session, LocalDateTime.now());

        Field finishedAt = Session.class.getDeclaredField("finishedAt");
        finishedAt.setAccessible(true);
        finishedAt.set(session, LocalDateTime.now().plusMinutes(1));

        Field votes = Session.class.getDeclaredField("votes");
        votes.setAccessible(true);
        votes.set(session, Collections.singletonList(vote));

        member = mock(Member.class);

        param = new CreateVoteParam();
        param.setSessionId("session-1");
        param.setMemberId("member-1");
        param.setVote("S");
    }

    @Test
    void shouldRegisterVoteSuccessfully() throws Exception {
        when(sessionService.getSessionByIdOrException(anyString())).thenReturn(session);
        when(memberService.getMemberByIdOrException(anyString())).thenReturn(member);
        when(voteService.getMemberVoteInSession(session, member)).thenReturn(Optional.empty());

        Vote vote = new Vote(session, member, VoteOptionEnum.YES);
        when(voteService.createVote(session, member, VoteOptionEnum.YES)).thenReturn(vote);

        aggregation.registerVote(param);

        verify(sessionService).getSessionByIdOrException(anyString());
        verify(memberService).getMemberByIdOrException(anyString());
        verify(voteService).getMemberVoteInSession(session, member);
        verify(voteService).createVote(session, member, VoteOptionEnum.YES);
    }

    @Test
    void shouldThrowIfSessionIsClosed() throws Exception {
        when(sessionService.getSessionByIdOrException(anyString())).thenReturn(session);
        when(memberService.getMemberByIdOrException(anyString())).thenReturn(member);

        doThrow(new SessionClosedException()).when(session).validateIfOpen();

        assertThrows(SessionClosedException.class, () -> {
            aggregation.registerVote(param);
        });

        verify(session).validateIfOpen();
    }

    @Test
    void shouldThrowIfMemberIsNotAbleToVote() throws Exception {
        when(sessionService.getSessionByIdOrException(anyString())).thenReturn(session);
        when(memberService.getMemberByIdOrException(anyString())).thenReturn(member);

        doNothing().when(session).validateIfOpen();
        doThrow(new MemberNotAllowedException()).when(member).validateAbleToVote();

        assertThrows(MemberNotAllowedException.class, () -> {
            aggregation.registerVote(param);
        });

        verify(member).validateAbleToVote();
    }

    @Test
    void shouldThrowIfMemberAlreadyVoted() throws Exception {
        when(sessionService.getSessionByIdOrException(anyString())).thenReturn(session);
        when(memberService.getMemberByIdOrException(anyString())).thenReturn(member);

        doNothing().when(session).validateIfOpen();
        doNothing().when(member).validateAbleToVote();

        when(voteService.getMemberVoteInSession(session, member))
                .thenReturn(Optional.of(new Vote(session, member, VoteOptionEnum.YES)));

        assertThrows(VoteAlreadyExistsException.class, () -> {
            aggregation.registerVote(param);
        });

        verify(voteService).getMemberVoteInSession(session, member);
    }
}

