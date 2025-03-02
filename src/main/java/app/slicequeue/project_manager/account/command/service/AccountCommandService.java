package app.slicequeue.project_manager.account.command.service;

import app.slicequeue.project_manager.account.command.dto.AccountAccessTokenRequest;
import app.slicequeue.project_manager.account.command.dto.AccountLoginRequest;
import app.slicequeue.project_manager.account.command.dto.AccountTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountCommandService {

    public AccountTokenResponse login(AccountLoginRequest request) {

        return null;
    }

    public AccountTokenResponse refreshToken(AccountAccessTokenRequest request) {
        return null;
    }
}

