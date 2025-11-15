package side.project.mirr.dto.response;

public record IdResponse(
        Long id
) {
    public static IdResponse of (Long id) {
        return new IdResponse(id);
    }
}
