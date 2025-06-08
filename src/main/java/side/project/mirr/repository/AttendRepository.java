package side.project.mirr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import side.project.mirr.domain.Attend;

import java.util.List;

public interface AttendRepository extends JpaRepository<Attend, Long> {
    List<Attend> findAllByGameId(Long gameId);


    @Query(value = "SELECT RANK() OVER(ORDER BY COUNT(a) DESC), p, COUNT(a) as attendCount " +
            "FROM Player p " +
            "LEFT JOIN Attend a ON a.player = p " +
            "GROUP BY p " +
            "ORDER BY attendCount DESC")
    Page<Object[]> countAttend(Pageable pageable);
}
