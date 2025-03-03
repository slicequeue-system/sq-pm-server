package app.slicequeue.project_manager.application.account.command.controller;

import app.slicequeue.project_manager.application.account.command.dto.AccountAccessTokenRequest;
import app.slicequeue.project_manager.application.account.command.dto.AccountLoginRequest;
import app.slicequeue.project_manager.application.account.command.dto.AccountRegisterRequest;
import app.slicequeue.project_manager.application.account.command.dto.AccountTokenResponse;
import app.slicequeue.project_manager.application.account.command.service.AccountCommandService;
import app.slicequeue.project_manager.common.dto.CommonResponse;
import app.slicequeue.project_manager.common.exception.BadRequestException;
import app.slicequeue.project_manager.domain.account.model.Account;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class AccountCommandController {

    private final AccountCommandService commandService;

    @PostMapping("/login")
    public AccountTokenResponse login(@RequestBody @Valid AccountLoginRequest request) {
        return commandService.login(request);
    }

    @PostMapping("/access-token")
    public AccountTokenResponse refreshAccessToken(
            @AuthenticationPrincipal Account account,
            @RequestBody @Valid AccountAccessTokenRequest request) {
        return commandService.refreshToken(account, request);
    }

    @PostMapping("/register")
    public CommonResponse<Void> create(@RequestHeader(value = "pmKey", required = false) String pmKey,
            @RequestBody @Valid AccountRegisterRequest request) {
        if (pmKey == null || pmKey.isBlank()) {
            throw new BadRequestException("권한 없음");
        }
        return commandService.createAccount(pmKey, request);
    }
}

