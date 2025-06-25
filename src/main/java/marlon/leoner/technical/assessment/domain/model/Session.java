package marlon.leoner.technical.assessment.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import marlon.leoner.technical.assessment.domain.dto.SessionDTO;
import marlon.leoner.technical.assessment.domain.exception.SessionException;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_session")
public class Session extends BaseEntity {

    @Setter
    @OneToOne
    private Topic topic;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "finished_at")
    private LocalDateTime finishedAt;

    public Session(Integer duration) {
        super();
        this.duration = duration;
        this.startedAt = LocalDateTime.now();
        this.finishedAt = startedAt.plusMinutes(duration);
    }

    public Session(Topic topic, Integer duration) {
        this(duration);
        this.topic = topic;
    }

    public boolean isClosed() {
        return LocalDateTime.now().isAfter(this.finishedAt);
    }

    public void validateIfClosed() throws SessionException {
        if (isClosed()) throw new SessionException("Sessão já está encerrada");
    }
}
