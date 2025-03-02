package app.slicequeue.project_manager.common.dto;

import app.slicequeue.project_manager.common.base.BaseRuntimeException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@JsonInclude(Include.NON_NULL)
public class CommonErrorResponse {

    private Integer code;

    private String message;

    private Map<String, Object> detail;

    private CommonErrorResponse() {}

    @Builder
    private CommonErrorResponse(Integer code, String message, Map<String, Object> detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public static CommonErrorResponse from(BaseRuntimeException ex) {
        return builder()
                .code(ex.getCode().value())
                .message(ex.getMessage())
                .detail(ex.getDetail())
                .build();
    }

    public static CommonErrorResponse from(RuntimeException ex) {
        return builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build();
    }
}
