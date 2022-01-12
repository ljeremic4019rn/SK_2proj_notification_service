package app.repository;

import app.domain.ActivationNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivationNotifRepository  extends JpaRepository<ActivationNotification, Long> {
}
