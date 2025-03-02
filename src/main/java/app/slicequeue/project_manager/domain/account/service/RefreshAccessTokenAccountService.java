package app.slicequeue.project_manager.domain.account.service;

import app.slicequeue.project_manager.application.account.command.dto.AccountAccessTokenRequest;
import app.slicequeue.project_manager.application.account.command.dto.AccountTokenResponse;
import app.slicequeue.project_manager.common.exception.BadRequestException;
import app.slicequeue.project_manager.config.security.JwtUtil;
import app.slicequeue.project_manager.domain.account.model.Account;
import app.slicequeue.project_manager.infrastructure.account.repository.AccountRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RefreshAccessTokenAccountService {

    private final JwtUtil jwtUtil;
    private final AccountRepository accountRepository;

    public AccountTokenResponse refresh(Account account, AccountAccessTokenRequest request, Instant at) {
        if (account.getRefreshToken() == null || !account.getRefreshToken().equals(request.getRefreshToken())) {
            throw new BadRequestException("리프레시 토큰 정보가 유효하지 않습니다. 다시 로그인 해주세요.");
        }

        if (account.getRefreshTokenExpiredAt() == null || Duration.between(account.getRefreshTokenExpiredAt(), at).toDays() >= 15) {
            throw new BadRequestException("리프레시 토큰 정보가 유효하지 않습니다. 다시 로그인 해주세요.");
        }

        JwtUtil.JwtTokenResult jwtTokenResult = jwtUtil.generateToken(account.getId(), List.of());
        account.generateRefreshToken(jwtTokenResult);
        accountRepository.saveAndFlush(account);

        return AccountTokenResponse.of(account, jwtTokenResult);
    }
}
