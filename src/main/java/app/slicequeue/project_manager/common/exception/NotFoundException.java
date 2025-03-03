package app.slicequeue.project_manager.common.exception;

import app.slicequeue.project_manager.common.base.BaseRuntimeException;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class NotFoundException  extends BaseRuntimeException {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message, null);
    }

    public NotFoundException(String message, Map<String, Object> detail) {
        super(HttpStatus.NOT_FOUND, message, detail);
    }

    public NotFoundException(IllegalArgumentException e) {
        super(HttpStatus.NOT_FOUND, e.getMessage(), null);
    }

    public NotFoundException(IllegalStateException e) {
        super(HttpStatus.NOT_FOUND, e.getMessage(), null);
    }
}
