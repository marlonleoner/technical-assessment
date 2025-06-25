package marlon.leoner.technical.assessment.aggregation;

import lombok.RequiredArgsConstructor;
import marlon.leoner.technical.assessment.domain.dto.TopicDTO;
import marlon.leoner.technical.assessment.domain.param.CreateTopicParam;
import marlon.leoner.technical.assessment.domain.param.CreateVoteParam;
import marlon.leoner.technical.assessment.domain.model.Member;
import marlon.leoner.technical.assessment.domain.model.Topic;
import marlon.leoner.technical.assessment.domain.enums.VoteOptionEnum;
import marlon.leoner.technical.assessment.domain.exception.ObjectAlreadyExistsException;
import marlon.leoner.technical.assessment.domain.exception.ObjectNotFoundException;
import marlon.leoner.technical.assessment.domain.exception.SessionException;
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

    public TopicDTO createTopic(CreateTopicParam params) {
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
}
