package side.project.mirr.dto.response;

public record Response<T>(
        String resultCode,
        T result
) {
    public static Response<Void> success() {
        return new Response<Void> ("success", null);
    }

    public static <T> Response<T> success(T result) {
        return new Response<T> ("success", result);
    }

//    public static Response<String> error(ErrorCode error, String message) {
//        String msg = message;
//        if (msg == null) {
//            msg = error.getMessage();
//        }
//        return new Response<String>(error.name, msg);
//    }

}
