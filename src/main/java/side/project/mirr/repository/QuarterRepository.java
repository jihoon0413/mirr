package side.project.mirr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import side.project.mirr.domain.Quarter;

import java.util.List;

public interface QuarterRepository extends JpaRepository<Quarter, Long> {
    List<Quarter> findAllByGameId(Long id);
}
