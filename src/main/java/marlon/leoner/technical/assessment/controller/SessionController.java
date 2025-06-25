package marlon.leoner.technical.assessment.controller;

import lombok.AllArgsConstructor;
import marlon.leoner.technical.assessment.aggregation.SessionAggregation;
import marlon.leoner.technical.assessment.domain.dto.SessionDTO;
import marlon.leoner.technical.assessment.domain.exception.ObjectAlreadyExistsException;
import marlon.leoner.technical.assessment.domain.exception.ObjectNotFoundException;
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

    @PostMapping
    public ResponseEntity<SessionDTO> createSession(@RequestBody CreateSessionParam params) throws ObjectNotFoundException, ObjectAlreadyExistsException {
        return ResponseEntity.ok(aggregation.createSession(params));
    }
}
