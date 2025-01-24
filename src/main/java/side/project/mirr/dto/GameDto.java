package side.project.mirr.dto;

import side.project.mirr.domain.Game;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record GameDto(
        Long id,
        String stadium,
        LocalDateTime matchDay
) {

    public static GameDto of(String stadium, String matchDay) {
        return new GameDto(
                0L,
                stadium,
                LocalDate.parse(matchDay).atStartOfDay()
        );
    }

    public static GameDto from (Game game) {
        return new GameDto(
                game.getId(),
                game.getStadium(),
                game.getMatchDay()
        );
    }
}
