package side.project.mirr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import side.project.mirr.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
