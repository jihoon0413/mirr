package side.project.mirr.dto;

import side.project.mirr.domain.Player;
import side.project.mirr.domain.eNum.Position;

public record PlayerDto(
        Long id,
        String name,
        Position pos,
        int backNum
) {


    public static PlayerDto from(Player player) {
        return new PlayerDto(
                player.getId(),
                player.getName(),
                player.getPos(),
                player.getBackNum()
        );
    }

}
