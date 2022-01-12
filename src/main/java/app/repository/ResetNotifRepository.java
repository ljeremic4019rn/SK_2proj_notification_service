package app.repository;

import app.domain.ResetNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetNotifRepository extends JpaRepository<ResetNotification, Long> {
}
