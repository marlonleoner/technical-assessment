package marlon.leoner.technical.assessment.aggregation;

import lombok.RequiredArgsConstructor;
import marlon.leoner.technical.assessment.dto.TopicDTO;
import marlon.leoner.technical.assessment.dto.request.CreateTopicRequest;
import marlon.leoner.technical.assessment.dto.request.CreateVoteRequest;
import marlon.leoner.technical.assessment.dto.request.CreateVotingSessionRequest;
import marlon.leoner.technical.assessment.model.Member;
import marlon.leoner.technical.assessment.model.Topic;
import marlon.leoner.technical.assessment.model.enums.VoteOptionEnum;
import marlon.leoner.technical.assessment.model.exception.ObjectAlreadyExistsException;
import marlon.leoner.technical.assessment.model.exception.ObjectNotFoundException;
import marlon.leoner.technical.assessment.model.exception.SessionException;
import marlon.leoner.technical.assessment.service.MemberService;
import marlon.leoner.technical.assessment.service.SessionService;
import marlon.leoner.technical.assessment.service.TopicService;
import marlon.leoner.technical.assessment.service.VoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TopicAggregation {

    private final MemberService memberService;
    private final TopicService topicService;
    private final SessionService sessionService;
    private final VoteService voteService;

    public TopicDTO createTopic(CreateTopicRequest params) {
        Topic topic = topicService.createTopic(params);
        return topic.toDTO();
    }

    public List<TopicDTO> getAllTopics() {
        return topicService.getAllTopics()
                .stream()
                .map(Topic::toDTO)
                .toList();
    }

    public TopicDTO getTopic(String topicId) throws ObjectNotFoundException {
        Topic topic = getTopicByIdOrException(topicId);
        return topic.toDTO();
    }

    private Topic getTopicByIdOrException(String topicId) throws ObjectNotFoundException {
        return topicService.getTopicByIdOrException(topicId);
    }

    private Member getMemberByIdOrException(String memberId) throws ObjectNotFoundException {
        return memberService.getMemberByIdOrException(memberId);
    }

    public void createVotingSession(String topicId, CreateVotingSessionRequest params) throws ObjectNotFoundException, ObjectAlreadyExistsException {
        Topic topic = getTopicByIdOrException(topicId);

        validateSessionCreation(topic);

        sessionService.createSession(topic, params.getDuration());
    }

    private void validateSessionCreation(Topic topic) throws ObjectAlreadyExistsException {
        sessionService.validateSessionAlreadyExists(topic.getSession());
    }

    public void registerVote(String topicId, CreateVoteRequest params) throws ObjectNotFoundException, ObjectAlreadyExistsException, SessionException {
        Topic topic = getTopicByIdOrException(topicId);
        Member member = getMemberByIdOrException(params.getMemberId());

        sessionService.validateSessionExists(topic.getSession());
        sessionService.validateSessionOpened(topic.getSession());
        voteService.validateMemberVoteInTopic(member, topic);

        voteService.createVote(topic, member, VoteOptionEnum.valueOf(params.getVote()));
    }
}
