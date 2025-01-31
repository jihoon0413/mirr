package side.project.mirr.dto;

import side.project.mirr.domain.Point;
import side.project.mirr.domain.eNum.PointType;

import java.time.LocalDateTime;

public record PointDto(
        Long id,
        PointType type,
        LocalDateTime dateInfo,
        PlayerDto playerDto
) {

    public static PointDto from(Point point) {
        return new PointDto(point.getId(), point.getType(), point.getDateInfo(), PlayerDto.from(point.getPlayer()));
    }

}
