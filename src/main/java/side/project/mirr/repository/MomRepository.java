package side.project.mirr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import side.project.mirr.domain.Mom;

import java.util.List;
import java.util.Optional;

public interface MomRepository extends JpaRepository<Mom, Long> {
    List<Mom> findAllByGameId(Long gameId);
    Optional<Mom> findByGameIdAndPlayerId(Long aLong, Long id);

    void deleteByGameIdAndPlayerId(Long gameId, Long playerId);

    Long countByPlayerId(Long id);
}
