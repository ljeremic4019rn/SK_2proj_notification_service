package app.repository;

import app.domain.ActivationNotif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivationNotifRepository  extends JpaRepository<ActivationNotif, Long> {
}
