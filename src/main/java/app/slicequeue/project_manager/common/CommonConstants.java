package app.slicequeue.project_manager.common;

public class CommonConstants {

    public enum Message {
        VALID_NOT_NULL("값이 NULL 일 수 없습니다."),
        VALID_NOT_EMPTY("값이 비어있을 수 없습니다."),
        VALID_NOT_BLANK("값이 공백일 수 없습니다.");

        private final String message;

        Message(String message) {
            this.message = message;
        }

        public String message(String prefix) {
            if (prefix == null || prefix.isEmpty() || prefix.isBlank())
                return this.message;

            return String.format("%s %s", prefix, message);
        }
    }

}
