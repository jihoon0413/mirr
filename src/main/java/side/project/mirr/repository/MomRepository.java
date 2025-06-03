package side.project.mirr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import side.project.mirr.domain.Mom;

import java.util.List;
import java.util.Optional;

public interface MomRepository extends JpaRepository<Mom, Long> {
    List<Mom> findAllByGameId(Long gameId);
    Optional<Mom> findByGameIdAndPlayerId(Long aLong, Long id);

    void deleteByGameIdAndPlayerId(Long gameId, Long playerId);

    Long countByPlayerId(Long id);

    @Query(value = "SELECT p, COUNT(m) as momCount " +
            "FROM Player p " +
            "LEFT JOIN Mom m ON m.player = p " +
            "GROUP BY p " +
            "ORDER BY momCount DESC")
    Page<Object[]> countMom(Pageable pageable);
}
