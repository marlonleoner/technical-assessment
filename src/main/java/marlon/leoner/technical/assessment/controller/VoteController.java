package marlon.leoner.technical.assessment.controller;

import lombok.AllArgsConstructor;
import marlon.leoner.technical.assessment.aggregation.VoteAggregation;
import marlon.leoner.technical.assessment.domain.param.CreateVoteParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/vote")
public class VoteController {

    private final VoteAggregation aggregation;

    @PostMapping
    public ResponseEntity<Void> registerVote(@RequestBody CreateVoteParam params) throws Exception {
        aggregation.registerVote(params);
        return ResponseEntity.noContent().build();
    }
}
