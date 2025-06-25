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
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "value")
    @Enumerated(EnumType.STRING)
    private VoteOptionEnum value;

    public Vote(Topic topic, Member member, VoteOptionEnum value) {
        super();
        this.topic = topic;
        this.member = member;
        this.value = value;
    }
}
