package app.repository;

import app.domain.ActivationNotif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ActivationNotifRepository  extends JpaRepository<ActivationNotif, Long> {

}
