package side.project.mirr.dto.response;

import lombok.Getter;
import side.project.mirr.dto.PlayerDto;

public record RankingResponse(
        PlayerDto playerDto,
        Long count
) {

    public static RankingResponse from(PlayerDto playerDto, Long count) {
        return new RankingResponse(
                playerDto,
                count
        );
    };


}
