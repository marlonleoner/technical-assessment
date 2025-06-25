package marlon.leoner.technical.assessment.aggregation;

import marlon.leoner.technical.assessment.domain.dto.ResultDTO;
import marlon.leoner.technical.assessment.domain.dto.SessionDTO;
import marlon.leoner.technical.assessment.domain.enums.VoteOptionEnum;
import marlon.leoner.technical.assessment.domain.exception.BaseException;
import marlon.leoner.technical.assessment.domain.exception.SessionAlreadyExistsException;
import marlon.leoner.technical.assessment.domain.exception.SessionNotFoundException;
import marlon.leoner.technical.assessment.domain.model.Session;
import marlon.leoner.technical.assessment.domain.model.Topic;
import marlon.leoner.technical.assessment.domain.model.Vote;
import marlon.leoner.technical.assessment.domain.param.CreateSessionParam;
import marlon.leoner.technical.assessment.service.SessionService;
import marlon.leoner.technical.assessment.service.TopicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionAggregationTest {

    @Mock
    private TopicService topicService;

    @Mock
    private SessionService sessionService;

    @InjectMocks
    private SessionAggregation aggregation;

    private Session session;

    private Topic topic;

    @BeforeEach
    void setup() throws IllegalAccessException, NoSuchFieldException {
        topic = new Topic();

        Vote vote = new Vote();
        Field value = Vote.class.getDeclaredField("value");
        value.setAccessible(true);
        value.set(vote, VoteOptionEnum.YES);

        session = new Session(1);
        session.setTopic(topic);

        Field startedAt = Session.class.getDeclaredField("startedAt");
        startedAt.setAccessible(true);
        startedAt.set(session, LocalDateTime.of(2025, 6, 25, 18, 10, 0));

        Field finishedAt = Session.class.getDeclaredField("finishedAt");
        finishedAt.setAccessible(true);
        finishedAt.set(session, LocalDateTime.of(2025, 6, 25, 18, 11, 0));

        Field votes = Session.class.getDeclaredField("votes");
        votes.setAccessible(true);
        votes.set(session, Collections.singletonList(vote));
    }

    @Test
    void shouldReturnAllSessionsAsDTOs() {
        when(sessionService.getAllSessions()).thenReturn(List.of(session));

        List<SessionDTO> result = aggregation.getAllSessions();

        assertEquals(1, result.size());
        verify(sessionService).getAllSessions();
    }

    @Test
    void shouldReturnSessionDTOById() throws SessionNotFoundException {
        when(sessionService.getSessionByIdOrException(anyString())).thenReturn(session);

        SessionDTO result = aggregation.getSession(anyString());

        assertNotNull(result);
        verify(sessionService).getSessionByIdOrException(anyString());
    }

    @Test
    void shouldCreateSessionSuccessfully() throws BaseException {
        CreateSessionParam param = new CreateSessionParam();
        param.setTopicId("topic-1");

        when(sessionService.getSessionByTopicId(anyString())).thenReturn(Optional.empty());
        when(topicService.getTopicByIdOrException(anyString())).thenReturn(topic);
        when(sessionService.save(any(Session.class))).thenReturn(session);

        SessionDTO result = aggregation.createSession(param);

        assertNotNull(result);
        verify(sessionService).getSessionByTopicId(anyString());
        verify(topicService).getTopicByIdOrException(anyString());
        verify(sessionService).save(any(Session.class));
    }

    @Test
    void shouldThrowWhenSessionAlreadyExists() {
        CreateSessionParam param = new CreateSessionParam();
        param.setTopicId("topic-1");

        when(sessionService.getSessionByTopicId(anyString())).thenReturn(Optional.of(session));

        assertThrows(SessionAlreadyExistsException.class, () -> {
            aggregation.createSession(param);
        });

        verifyNoMoreInteractions(topicService, sessionService);
    }

    @Test
    void shouldReturnSessionResult() throws Exception {
        when(sessionService.getSessionByIdOrException(anyString())).thenReturn(session);

        ResultDTO result = aggregation.getResult(anyString());

        assertNotNull(result);
        verify(sessionService).getSessionByIdOrException(anyString());
        assertEquals(1, result.getTotalVotes());
        assertEquals(0, result.getVotesNo());
        assertEquals(1, result.getVotesYes());
    }
}
