package app.slicequeue.project_manager.common.exception;

import app.slicequeue.project_manager.common.base.BaseRuntimeException;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.function.Supplier;

public class NotFoundException  extends BaseRuntimeException {

    public static Supplier<NotFoundException> getSupplierNotFoundException(String prefix) {
        return () -> new NotFoundException(String.format("%s 데이터를 찾을 수 없습니다", prefix));
    }

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
