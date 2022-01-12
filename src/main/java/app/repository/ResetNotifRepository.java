package app.repository;

import app.domain.ResetNotif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetNotifRepository extends JpaRepository<ResetNotif, Long> {
}
