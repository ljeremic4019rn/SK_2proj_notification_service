package app.repository;

import app.domain.ActivationNotif;
import app.domain.ReservationNotif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ReservationNotifRepository extends JpaRepository<ReservationNotif, Long> {

}
