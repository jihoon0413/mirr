package side.project.mirr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import side.project.mirr.domain.Attend;

import java.util.List;

public interface AttendRepository extends JpaRepository<Attend, Long> {
    List<Attend> findAllByGameId(Long gameId);
}
