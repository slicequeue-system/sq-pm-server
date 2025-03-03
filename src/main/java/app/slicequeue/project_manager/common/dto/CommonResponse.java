// CommonResponse.java

package app.slicequeue.project_manager.common.dto;

import lombok.Getter;

/**
 * CommonResponse, 공통 응답 객체
 */
@Getter
public class CommonResponse<T> {

    private static final String MESSAGE_OK = "OK";
    private static final String MESSAGE_FAIL = "FAIL";


    /**
     * 성공 실패 여부
     */
    private final boolean success;
    /**
     * 메세지
     */
    private final String message;
    /**
     * 응답 데이터
     */
    private final T data;

    private CommonResponse(boolean success, String message, T data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }

    // 성공

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>(true, MESSAGE_OK, null);
    }

    public static <T> CommonResponse<T> success(String message) {
        return new CommonResponse<>(true, message, null);
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(true, MESSAGE_OK, data);
    }

    public static <T> CommonResponse<T> success(String message, T data) {
        return new CommonResponse<>(true, message, data);
    }

    // 실패

    public static <T> CommonResponse<T> fail() {
        return new CommonResponse<>(false, MESSAGE_FAIL, null);
    }

    public static <T> CommonResponse<T> fail(String message) {
        return new CommonResponse<>(false, message, null);
    }

    public static <T> CommonResponse<T> fail(T data) {
        return new CommonResponse<>(false, MESSAGE_FAIL, data);
    }

    public static <T> CommonResponse<T> fail(String message, T data) {
        return new CommonResponse<>(false, message, data);
    }
}
