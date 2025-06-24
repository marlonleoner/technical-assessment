package marlon.leoner.technical.assessment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import marlon.leoner.technical.assessment.dto.TopicDTO;
import marlon.leoner.technical.assessment.model.enums.TopicStatusEnum;
import marlon.leoner.technical.assessment.util.Utils;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "topic")
@Getter
@Setter
public class Topic {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private TopicStatusEnum status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Topic(String name, String description) {
        this.name = name;
        this.slug = Utils.generateSlug(name);
        this.description = description;
        this.status = TopicStatusEnum.CREATED;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public TopicDTO toDTO() {
        return new TopicDTO(this);
    }
}
