package app.slicequeue.project_manager.application.account.command.controller;

import app.slicequeue.project_manager.application.account.command.dto.AccountAccessTokenRequest;
import app.slicequeue.project_manager.application.account.command.dto.AccountLoginRequest;
import app.slicequeue.project_manager.application.account.command.dto.AccountTokenResponse;
import app.slicequeue.project_manager.application.account.command.service.AccountCommandService;
import app.slicequeue.project_manager.domain.account.model.Account;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class AccountCommandController {

    private final AccountCommandService commandService;

    @PostMapping("/login")
    public AccountTokenResponse login(@RequestBody AccountLoginRequest request) {
        return commandService.login(request);
    }

    @PostMapping("/access-token")
    public AccountTokenResponse refreshAccessToken(
            @AuthenticationPrincipal Account account,
            @RequestBody @Valid AccountAccessTokenRequest request)  {
        return commandService.refreshToken(account, request);
    }
}

