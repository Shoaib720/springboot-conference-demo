package ml.hadiya.conferencedemo.repositories;

import ml.hadiya.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
