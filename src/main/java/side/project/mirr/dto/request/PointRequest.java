package side.project.mirr.dto.request;

import side.project.mirr.domain.Player;
import side.project.mirr.domain.Point;
import side.project.mirr.domain.Quarter;
import side.project.mirr.domain.eNum.PointType;

import java.time.LocalDateTime;
import java.util.List;

public record PointRequest(
        Long quarterId,
        List<Long> playerId,
        PointType type

) {

    public static Point toEntity(Quarter quarter, Player player, PointType type, LocalDateTime dateInfo) {
        return Point.of(quarter, player, type, dateInfo);
    }

}
