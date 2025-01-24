package side.project.mirr.dto.request;

import side.project.mirr.dto.GameDto;

import java.time.LocalDateTime;

public record GameRequest(
        String stadium,
        String matchDay
) {

}
