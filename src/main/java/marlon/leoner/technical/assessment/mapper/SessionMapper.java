package marlon.leoner.technical.assessment.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import marlon.leoner.technical.assessment.domain.dto.SessionDTO;
import marlon.leoner.technical.assessment.domain.model.Session;
import marlon.leoner.technical.assessment.domain.param.CreateSessionParam;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessionMapper {

    public static Session toEntity(CreateSessionParam params) {
        return new Session(params.getDuration());
    }

    public static SessionDTO toDTO(Session session) {
        SessionDTO dto = new SessionDTO();
        dto.setId(session.getId());
        dto.setTopicId(session.getTopic().getId());
        dto.setStartTime(session.getStartedAt());
        dto.setFinishTime(session.getFinishedAt());
        dto.setClosed(session.isClosed());

        return dto;
    }
}
