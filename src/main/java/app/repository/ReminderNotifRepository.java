package app.repository;

import app.domain.ReminderNotif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderNotifRepository extends JpaRepository<ReminderNotif, Long> {
}
