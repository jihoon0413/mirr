package side.project.mirr.dto.request;

import side.project.mirr.domain.Game;
import side.project.mirr.domain.Quarter;

public record QuarterRequest(
        Long gameId,
        Integer quarterNum,
        String opponent,
        int point,
        int losePoint
) {

    public static Quarter toEntity(Game game, QuarterRequest quarterDto) {
        return Quarter.of(game, quarterDto.quarterNum, quarterDto.opponent, quarterDto.point, quarterDto.losePoint);
    }

}
