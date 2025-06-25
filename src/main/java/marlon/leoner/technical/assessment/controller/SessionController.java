package marlon.leoner.technical.assessment.controller;

import lombok.AllArgsConstructor;
import marlon.leoner.technical.assessment.aggregation.SessionAggregation;
import marlon.leoner.technical.assessment.domain.dto.ResultDTO;
import marlon.leoner.technical.assessment.domain.dto.SessionDTO;
import marlon.leoner.technical.assessment.domain.exception.BaseException;
import marlon.leoner.technical.assessment.domain.exception.ObjectNotFoundException;
import marlon.leoner.technical.assessment.domain.exception.SessionInProgressException;
import marlon.leoner.technical.assessment.domain.exception.SessionNotFoundException;
import marlon.leoner.technical.assessment.domain.param.CreateSessionParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/session")
public class SessionController {

    private final SessionAggregation aggregation;

    @GetMapping
    public ResponseEntity<List<SessionDTO>> getAllSessions() {
        return ResponseEntity.ok(aggregation.getAllSessions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionDTO> getSession(@PathVariable("id") String sessionId) throws SessionNotFoundException {
        return ResponseEntity.ok(aggregation.getSession(sessionId));
    }

    @PostMapping
    public ResponseEntity<SessionDTO> createSession(@RequestBody CreateSessionParam params) throws BaseException {
        return ResponseEntity.ok(aggregation.createSession(params));
    }

    @GetMapping("/{id}/result")
    public ResponseEntity<ResultDTO> getResult(@PathVariable("id") String topicId) throws ObjectNotFoundException, SessionInProgressException {
        return ResponseEntity.ok(aggregation.getResult(topicId));
    }
}
