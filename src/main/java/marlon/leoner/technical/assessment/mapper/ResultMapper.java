package marlon.leoner.technical.assessment.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import marlon.leoner.technical.assessment.domain.dto.ResultDTO;
import marlon.leoner.technical.assessment.domain.enums.ResultEnum;
import marlon.leoner.technical.assessment.domain.model.Session;
import marlon.leoner.technical.assessment.domain.model.Vote;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultMapper {

    public static ResultDTO toResult(Session session) {
        List<Vote> votes = session.getVotes();

        int yes = Math.toIntExact(votes.stream().filter(Vote::isYes).count());
        int no = Math.toIntExact(votes.stream().filter(Vote::isNo).count());

        ResultDTO dto = new ResultDTO();
        dto.setTopic(TopicMapper.toDTO(session.getTopic()));
        dto.setTotalVotes(votes.size());
        dto.setVotesYes(yes);
        dto.setVotesNo(no);

        ResultEnum result = ResultEnum.calculatedResult(yes, no);
        dto.setResult(result.getDescription());

        return dto;
    }
}
