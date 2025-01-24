package side.project.mirr.dto;

import side.project.mirr.domain.Quarter;

public record QuarterDto(
        int quarterNum,
        String opponent,
        int point,
        int losePoint
) {

    public static QuarterDto from(Quarter quarter) {
        return new QuarterDto(
                quarter.getQuarterNum(),
                quarter.getOpponent(),
                quarter.getPoint(),
                quarter.getLosePoint()
        );
    }
}
