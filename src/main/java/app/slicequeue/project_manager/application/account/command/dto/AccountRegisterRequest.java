package app.slicequeue.project_manager.application.account.command.dto;

import app.slicequeue.project_manager.common.util.StringPatternMatchingUtil.RegexValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import static app.slicequeue.project_manager.common.CommonConstants.ValidMessage.POSTFIX_NOT_BLANK;
import static app.slicequeue.project_manager.common.CommonConstants.ValidMessage.POSTFIX_NOT_NULL;

@Getter
public class AccountRegisterRequest {

    @NotNull(message = "email" + POSTFIX_NOT_NULL)
    @NotBlank(message = "email" + POSTFIX_NOT_BLANK)
    @Pattern(regexp = RegexValue.VALID_EMAIL_REGEX, message =
            "형식이 유효하지 않습니다. " + RegexValue.VALID_EMAIL_REGEX_DESCRIPTION)
    private final String email;

    @NotNull(message = "secretKey" + POSTFIX_NOT_NULL)
    @NotBlank(message = "secretKey" + POSTFIX_NOT_BLANK)
    @Pattern(regexp = RegexValue.VALID_PASSWORD_LV3_REGEX, message =
             "형식이 유효하지 않습니다. " + RegexValue.VALID_PASSWORD_LV3_REGEX_DESCRIPTION)
    private final String secretKey;

    private final String nickname;

    public AccountRegisterRequest(String email, String secretKey, String nickname) {
        this.email = email;
        this.secretKey = secretKey;
        this.nickname = nickname;
    }
}
