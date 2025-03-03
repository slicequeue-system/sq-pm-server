package app.slicequeue.project_manager.application.account.command.dto;

import app.slicequeue.project_manager.config.security.JwtUtil;
import app.slicequeue.project_manager.domain.account.model.Account;
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

    public static AccountTokenResponse of(Account account, JwtUtil.JwtTokenResult jwtTokenResult) {
        return new AccountTokenResponse(account.getRefreshToken(), jwtTokenResult.accessToken(),
                jwtTokenResult.expiredDate().toInstant(),
                account.getRefreshTokenExpiredAt());
    }
}
