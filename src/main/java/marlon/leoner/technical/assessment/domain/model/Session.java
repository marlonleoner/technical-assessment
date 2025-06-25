package marlon.leoner.technical.assessment.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import marlon.leoner.technical.assessment.domain.exception.SessionClosedException;
import marlon.leoner.technical.assessment.domain.exception.SessionInProgressException;

import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "opened_at")
    private LocalDateTime startedAt;

    @Column(name = "closed_at")
    private LocalDateTime finishedAt;

    @OneToMany(mappedBy = "session", fetch = FetchType.EAGER)
    private List<Vote> votes;

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

    public void validateIfOpen() throws SessionClosedException {
        if (isClosed()) throw new SessionClosedException();
    }

    public void validateIfClosed() throws SessionInProgressException {
        if (!isClosed()) throw new SessionInProgressException();
    }
}
