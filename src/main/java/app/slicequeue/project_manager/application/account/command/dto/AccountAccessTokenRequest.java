package app.slicequeue.project_manager.application.account.command.dto;

import app.slicequeue.project_manager.common.CommonConstants;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

import static app.slicequeue.project_manager.common.CommonConstants.ValidMessage.*;

@Getter
public class AccountAccessTokenRequest {

    @NotNull(message = "refreshToken" + POSTFIX_NOT_NULL)
    private final UUID refreshToken;

    public AccountAccessTokenRequest(UUID refreshToken) {
        this.refreshToken = refreshToken;
    }
}
