package app.slicequeue.project_manager.application.account.command.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class AccountAccessTokenRequest {

    private final UUID refreshToken;

    public AccountAccessTokenRequest(UUID refreshToken) {
        this.refreshToken = refreshToken;
    }
}
