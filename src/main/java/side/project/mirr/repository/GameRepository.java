package side.project.mirr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import side.project.mirr.domain.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}
