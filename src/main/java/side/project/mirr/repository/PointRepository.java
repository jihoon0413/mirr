package side.project.mirr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import side.project.mirr.domain.Point;
import side.project.mirr.domain.eNum.PointType;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findAllByQuarterId(Long quarterId);

    @Query(value = "SELECT RANK() OVER(ORDER BY COUNT(pt) DESC), p, COUNT(pt) as goalCount " +
            "FROM Player p " +
            "LEFT JOIN Point pt ON pt.player = p AND pt.type = 'GOAL'" +
            "GROUP BY p " +
            "ORDER BY goalCount DESC"
    )
    Page<Object[]> countGoal(Pageable pageable);

    @Query(value = "SELECT RANK() OVER(ORDER BY COUNT(pt) DESC), p, COUNT(pt) as assistCount " +
            "FROM Player p " +
            "LEFT JOIN Point pt ON pt.player = p AND pt.type = 'ASSIST'" +
            "GROUP BY p " +
            "ORDER BY assistCount DESC")
    Page<Object[]> countAssist(Pageable pageable);

}
