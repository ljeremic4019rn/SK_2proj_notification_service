package app.repository;

import app.domain.ReminderNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderNotifRepository extends JpaRepository<ReminderNotification, Long> {
}
