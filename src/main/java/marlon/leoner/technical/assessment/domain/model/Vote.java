package marlon.leoner.technical.assessment.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import marlon.leoner.technical.assessment.domain.enums.VoteOptionEnum;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_vote")
public class Vote extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "value")
    @Enumerated(EnumType.STRING)
    private VoteOptionEnum value;

    public Vote(Session session, Member member, VoteOptionEnum value) {
        super();
        this.session = session;
        this.member = member;
        this.value = value;
    }

    public Boolean isYes() {
        return VoteOptionEnum.YES.equals(this.value);
    }

    public Boolean isNo() {
        return VoteOptionEnum.NO.equals(this.value);
    }
}
