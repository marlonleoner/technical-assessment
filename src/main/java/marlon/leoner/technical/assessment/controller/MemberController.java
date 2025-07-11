package marlon.leoner.technical.assessment.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import marlon.leoner.technical.assessment.aggregation.MemberAggregation;
import marlon.leoner.technical.assessment.domain.dto.MemberDTO;
import marlon.leoner.technical.assessment.domain.param.CreateMemberParam;
import marlon.leoner.technical.assessment.domain.exception.ObjectAlreadyExistsException;
import marlon.leoner.technical.assessment.domain.exception.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberAggregation aggregation;

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        return ResponseEntity.ok(aggregation.getAllMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getAllMembers(@PathVariable("id") String memberId) throws ObjectNotFoundException {
        return ResponseEntity.ok(aggregation.getMember(memberId));
    }

    @PostMapping
    public ResponseEntity<Void> createMember(@Valid @RequestBody CreateMemberParam params) throws ObjectAlreadyExistsException {
        MemberDTO member = aggregation.createMember(params);
        return ResponseEntity.created(URI.create("/member/" + member.getId())).build();
    }
}
