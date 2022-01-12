package app.repository;

import app.domain.ReservationNotif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationNotifRepository extends JpaRepository<ReservationNotif, Long> {
}
