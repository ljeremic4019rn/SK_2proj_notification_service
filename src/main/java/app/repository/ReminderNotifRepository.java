package app.repository;

import app.domain.ActivationNotif;
import app.domain.ReminderNotif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ReminderNotifRepository extends JpaRepository<ReminderNotif, Long> {

}
