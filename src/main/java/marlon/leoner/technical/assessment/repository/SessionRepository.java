package marlon.leoner.technical.assessment.repository;

import marlon.leoner.technical.assessment.domain.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {
    //
}
