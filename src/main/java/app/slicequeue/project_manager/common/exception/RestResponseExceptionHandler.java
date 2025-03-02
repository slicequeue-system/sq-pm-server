package app.slicequeue.project_manager.common.exception;

import app.slicequeue.project_manager.common.base.BaseRuntimeException;
import app.slicequeue.project_manager.common.dto.CommonErrorResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

    @NotNull
    private static ResponseEntity<Object> handleExceptionAndGetResponseEntity(BaseRuntimeException ex) {
        log.error(ex);
        return ResponseEntity.status(ex.getCode()).body(CommonErrorResponse.from(ex));
    }

}
