package side.project.mirr.dto.response;

import lombok.Getter;
import side.project.mirr.dto.PlayerDto;

public record RankingResponse(
        Long rank,
        PlayerDto playerDto,
        Long count
) {

    public static RankingResponse from(Long rank, PlayerDto playerDto, Long count) {
        return new RankingResponse(
                rank,
                playerDto,
                count
        );
    };


}
