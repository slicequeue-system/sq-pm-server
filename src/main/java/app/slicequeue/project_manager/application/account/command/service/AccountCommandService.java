package app.slicequeue.project_manager.application.account.command.service;

import app.slicequeue.project_manager.application.account.command.dto.AccountAccessTokenRequest;
import app.slicequeue.project_manager.application.account.command.dto.AccountLoginRequest;
import app.slicequeue.project_manager.application.account.command.dto.AccountTokenResponse;
import app.slicequeue.project_manager.domain.account.service.LoginAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountCommandService {

    private final LoginAccountService loginAccountService;

    public AccountTokenResponse login(AccountLoginRequest request) {
        return loginAccountService.login(request);
    }

    public AccountTokenResponse refreshToken(AccountAccessTokenRequest request) {
        return null;
    }
}

