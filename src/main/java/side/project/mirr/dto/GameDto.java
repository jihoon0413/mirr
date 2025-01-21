package side.project.mirr.dto;

import side.project.mirr.domain.Game;

import java.time.LocalDateTime;

public record GameDto(
        Long id,
        String opponent,
        LocalDateTime matchDay
) {


    public static GameDto from (Game game) {
        return new GameDto(
                game.getId(),
                game.getOpponent(),
                game.getMatchDay()
        );
    }


}
