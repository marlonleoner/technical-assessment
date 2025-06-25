package marlon.leoner.technical.assessment.service;

import marlon.leoner.technical.assessment.domain.exception.SessionNotFoundException;
import marlon.leoner.technical.assessment.domain.model.Session;
import marlon.leoner.technical.assessment.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @Mock
    private SessionRepository repository;

    @InjectMocks
    private SessionService sessionService;

    private Session session;

    @BeforeEach
    void setup() {
        session = new Session();
    }

    @Test
    void shouldReturnAllSessions() {
        when(repository.findAll()).thenReturn(List.of(session));

        List<Session> sessions = sessionService.getAllSessions();

        assertEquals(1, sessions.size());
        verify(repository).findAll();
    }

    @Test
    void shouldReturnSessionById() {
        when(repository.findById(anyString())).thenReturn(Optional.of(session));

        Optional<Session> found = sessionService.getSessionById(anyString());

        assertTrue(found.isPresent());
        verify(repository).findById(anyString());
    }

    @Test
    void shouldThrowWhenSessionNotFound() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(SessionNotFoundException.class, () -> {
            sessionService.getSessionByIdOrException(anyString());
        });
    }

    @Test
    void shouldSaveSession() {
        when(repository.save(any(Session.class))).thenReturn(session);

        Session saved = sessionService.save(session);

        assertNotNull(saved);
        verify(repository).save(session);
    }

    @Test
    void shouldReturnSessionByTopicId() {
        when(repository.findSessionByTopicId(anyString())).thenReturn(Optional.of(session));

        Optional<Session> result = sessionService.getSessionByTopicId(anyString());

        assertTrue(result.isPresent());
        verify(repository).findSessionByTopicId(anyString());
    }
}