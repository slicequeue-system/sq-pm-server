package app.slicequeue.project_manager.common;

import lombok.Getter;

import static app.slicequeue.project_manager.common.CommonConstants.ValidMessage.*;

public class CommonConstants {

    public static class ValidMessage {
        public static final String NOT_NULL = "값이 NULL 일 수 없습니다.";
        public static final String POSTFIX_NOT_NULL = " " + NOT_NULL;

        public static final String NOT_EMPTY = "값이 비어있을 수 없습니다.";
        public static final String POSTFIX_NOT_EMPTY = " " + NOT_EMPTY;
        public static final String NOT_BLANK = "값이 공백일 수 없습니다.";
        public static final String POSTFIX_NOT_BLANK = " " + NOT_BLANK;

    }



    public enum Message {
        VALID_NOT_NULL(NOT_NULL),
        VALID_NOT_EMPTY(NOT_EMPTY),
        VALID_NOT_BLANK(NOT_BLANK);

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
