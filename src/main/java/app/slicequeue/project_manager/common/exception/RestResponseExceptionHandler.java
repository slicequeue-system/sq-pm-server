package app.slicequeue.project_manager.common.exception;

import app.slicequeue.project_manager.common.base.BaseRuntimeException;
import app.slicequeue.project_manager.common.dto.CommonErrorResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseRuntimeException.class)
    ResponseEntity<Object> handleBaseRuntimeException(BaseRuntimeException ex) {
        log.error(ex);
        return handleExceptionAndGetResponseEntity(ex);
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        log.error(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CommonErrorResponse.from(ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                   HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        // 클라이언트가 잘못된 형식의 요청 데이터를 보낸 경우 발생하는 예외를 처리합니다.
        // 예: JSON 파싱 오류, 숫자 필드에 문자열 입력 등
        return new ResponseEntity<>(
                CommonErrorResponse.builder()
                        .code(400)
                        .message("요청 본문의 형식이 올바르지 않습니다.") // 적절한 오류 메시지 설정
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                   HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        // @Valid 어노테이션이 적용된 요청 객체에서 유효성 검사 실패 시 발생하는 예외를 처리합니다.
        // 예: 필수 필드 누락, 길이 제한 초과 등
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map<String, Object> detail = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            detail.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ResponseEntity<>(
                CommonErrorResponse.builder()
                        .code(400)
                        .message("입력값이 올바르지 않습니다.")
                        .detail(detail)
                        .build(),
                HttpStatus.BAD_REQUEST);
    }


    @NotNull
    private static ResponseEntity<Object> handleExceptionAndGetResponseEntity(BaseRuntimeException ex) {
        log.error(ex);
        return ResponseEntity.status(ex.getCode()).body(CommonErrorResponse.from(ex));
    }

}
