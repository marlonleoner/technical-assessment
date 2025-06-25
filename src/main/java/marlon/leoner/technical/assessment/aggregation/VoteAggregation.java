package marlon.leoner.technical.assessment.aggregation;

import lombok.RequiredArgsConstructor;
import marlon.leoner.technical.assessment.domain.enums.VoteOptionEnum;
import marlon.leoner.technical.assessment.domain.exception.ObjectAlreadyExistsException;
import marlon.leoner.technical.assessment.domain.exception.ObjectNotFoundException;
import marlon.leoner.technical.assessment.domain.exception.SessionException;
import marlon.leoner.technical.assessment.domain.model.Member;
import marlon.leoner.technical.assessment.domain.model.Session;
import marlon.leoner.technical.assessment.domain.model.Vote;
import marlon.leoner.technical.assessment.domain.param.CreateVoteParam;
import marlon.leoner.technical.assessment.service.IntegrationService;
import marlon.leoner.technical.assessment.service.MemberService;
import marlon.leoner.technical.assessment.service.SessionService;
import marlon.leoner.technical.assessment.service.VoteService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VoteAggregation {

    private final SessionService sessionService;
    private final MemberService memberService;
    private final VoteService voteService;
    private final IntegrationService integrationService;

    private Session getSessionOrException(String sessionId) throws ObjectNotFoundException {
        return sessionService.getSessionByIdOrException(sessionId);
    }

    private Member getMemberOrException(String memberId) throws ObjectNotFoundException {
        return memberService.getMemberByIdOrException(memberId);
    }

    public void registerVote(CreateVoteParam params) throws Exception {
        Session session = getSessionOrException(params.getSessionId());
        Member member = getMemberOrException(params.getMemberId());

        validateSessionClosed(session);
        validateMemberAbleToVote(member);
        validateMemberVoteInSession(session, member);

        VoteOptionEnum value = VoteOptionEnum.getInstance(params.getVote());
        voteService.createVote(session, member, value);
    }

    private void validateSessionClosed(Session session) throws SessionException {
        session.validateIfClosed();
    }

    private void validateMemberAbleToVote(Member member) throws Exception {
        member.validateAbleToVote();
    }

    private void validateMemberVoteInSession(Session session, Member member) throws ObjectAlreadyExistsException {
        Optional<Vote> vote = voteService.getMemberVoteInSession(session, member);
        if (vote.isPresent()) throw new ObjectAlreadyExistsException("O associado já votou nesta sessão");
    }
}
