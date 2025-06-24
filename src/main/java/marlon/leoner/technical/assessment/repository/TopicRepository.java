package marlon.leoner.technical.assessment.repository;

import marlon.leoner.technical.assessment.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, String> {
}
