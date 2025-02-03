package side.project.mirr.dto;

import side.project.mirr.domain.Attend;

public record AttendDto(
        Long id,
        GameDto gameDto,
        PlayerDto playerDto
) {

    public static AttendDto from(Attend attendDto) {
        return new AttendDto(
                attendDto.getId(),
                GameDto.from(attendDto.getGame()),
                PlayerDto.from(attendDto.getPlayer())
                );
    }

}
