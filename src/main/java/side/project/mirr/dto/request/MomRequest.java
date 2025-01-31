package side.project.mirr.dto.request;

import side.project.mirr.dto.PlayerDto;

import java.util.List;

public record MomRequest(
        Long gameId,
        List<Long> playerId
) {
}
