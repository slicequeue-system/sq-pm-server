package app.slicequeue.project_manager.account.command.dto;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class AccountTokenResponse {

    private final UUID refreshToken;

    private final String accessToken;

    private final Instant expiredAt;

    private AccountTokenResponse(UUID refreshToken, String accessToken, Instant expiredAt) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.expiredAt = expiredAt;
    }

    public static AccountTokenResponse of(UUID refreshToken, String accessToken, Instant expiredAt) {
        return new AccountTokenResponse(refreshToken, accessToken, expiredAt);
    }
}
