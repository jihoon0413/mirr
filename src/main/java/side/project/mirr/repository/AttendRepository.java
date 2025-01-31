package side.project.mirr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import side.project.mirr.domain.Attend;

public interface AttendRepository extends JpaRepository<Attend, Long> {
}
