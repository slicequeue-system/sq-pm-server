package app.slicequeue.project_manager.application.account.command.dto;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class AccountTokenResponse {

    private final UUID refreshToken;

    private final String accessToken;

    private final Instant expiredAt;

    private final Instant refreshTokenExpiredAt;

    private AccountTokenResponse(UUID refreshToken, String accessToken, Instant expiredAt, Instant refreshTokenExpiredAt) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.expiredAt = expiredAt;
        this.refreshTokenExpiredAt = refreshTokenExpiredAt;
    }

    public static AccountTokenResponse of(UUID refreshToken, Instant refreshTokenExpiredAt, String accessToken, Instant expiredAt) {
        return new AccountTokenResponse(refreshToken, accessToken, expiredAt, refreshTokenExpiredAt);
    }
}
