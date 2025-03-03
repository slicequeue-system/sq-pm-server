package app.slicequeue.project_manager.application.account.command.service;

import app.slicequeue.project_manager.application.account.command.dto.AccountAccessTokenRequest;
import app.slicequeue.project_manager.application.account.command.dto.AccountLoginRequest;
import app.slicequeue.project_manager.application.account.command.dto.AccountRegisterRequest;
import app.slicequeue.project_manager.application.account.command.dto.AccountTokenResponse;
import app.slicequeue.project_manager.common.dto.CommonResponse;
import app.slicequeue.project_manager.common.exception.BadRequestException;
import app.slicequeue.project_manager.domain.account.model.Account;
import app.slicequeue.project_manager.domain.account.service.CreateAccountService;
import app.slicequeue.project_manager.domain.account.service.LoginAccountService;
import app.slicequeue.project_manager.domain.account.service.RefreshAccessTokenAccountService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountCommandService {

    private final LoginAccountService loginAccountService;
    private final RefreshAccessTokenAccountService refreshAccessTokenAccountService;
    private final CreateAccountService createAccountService;

    @Value("${secret.super-key}")
    private String superKey;

    public AccountTokenResponse login(AccountLoginRequest request) {
        return loginAccountService.login(request);
    }

    public AccountTokenResponse refreshToken(Account account, AccountAccessTokenRequest request) {
        return refreshAccessTokenAccountService.refresh(account, request, Instant.now());
    }

    public CommonResponse<Void> createAccount(@NotNull String pmKey, @NotNull AccountRegisterRequest request) {
        if (!superKey.equals(pmKey)) {
            throw new BadRequestException("권한 없음");
        }
        createAccountService.create(request);
        return CommonResponse.success();
    }
}

