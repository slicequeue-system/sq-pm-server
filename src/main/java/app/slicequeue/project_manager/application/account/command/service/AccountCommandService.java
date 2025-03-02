package app.slicequeue.project_manager.application.account.command.service;

import app.slicequeue.project_manager.application.account.command.dto.AccountAccessTokenRequest;
import app.slicequeue.project_manager.application.account.command.dto.AccountLoginRequest;
import app.slicequeue.project_manager.application.account.command.dto.AccountTokenResponse;
import app.slicequeue.project_manager.domain.account.model.Account;
import app.slicequeue.project_manager.domain.account.service.LoginAccountService;
import app.slicequeue.project_manager.domain.account.service.RefreshAccessTokenAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountCommandService {

    private final LoginAccountService loginAccountService;
    private final RefreshAccessTokenAccountService refreshAccessTokenAccountService;

    public AccountTokenResponse login(AccountLoginRequest request) {
        return loginAccountService.login(request);
    }

    public AccountTokenResponse refreshToken(Account account, AccountAccessTokenRequest request) {
        return refreshAccessTokenAccountService.refresh(account, request, Instant.now());
    }
}

