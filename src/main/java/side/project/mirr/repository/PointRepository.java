package side.project.mirr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import side.project.mirr.domain.Point;
import side.project.mirr.domain.eNum.PointType;

public interface PointRepository extends JpaRepository<Point, Long> {
    int countByPlayerIdAndType(Long id, PointType type);
}
