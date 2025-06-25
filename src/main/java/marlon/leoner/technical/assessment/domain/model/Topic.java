package marlon.leoner.technical.assessment.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import marlon.leoner.technical.assessment.domain.enums.TopicStatusEnum;
import marlon.leoner.technical.assessment.util.Utils;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_topic")
public class Topic extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private TopicStatusEnum status;

    @OneToOne(mappedBy = "topic")
    private Session session;

    public Topic(String name, String description) {
        super();
        this.name = name;
        this.slug = Utils.generateSlug(name);
        this.description = description;
        this.status = TopicStatusEnum.CREATED;
    }
}
