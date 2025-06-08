package side.project.mirr.dto.response;

import side.project.mirr.dto.PlayerDto;

public record PlayerDetailResponse (
    PlayerDto playerDto,
    RankInfo score,
    RankInfo assist,
    RankInfo attend
) {
    public static PlayerDetailResponse of(PlayerDto playerDto, RankInfo score, RankInfo assis, RankInfo attend) {
        return new PlayerDetailResponse(playerDto, score, assis, attend);
    }


}
