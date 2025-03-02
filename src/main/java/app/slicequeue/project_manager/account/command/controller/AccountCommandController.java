package app.slicequeue.project_manager.account.command.controller;

import app.slicequeue.project_manager.account.command.dto.AccountAccessTokenRequest;
import app.slicequeue.project_manager.account.command.dto.AccountLoginRequest;
import app.slicequeue.project_manager.account.command.dto.AccountTokenResponse;
import app.slicequeue.project_manager.account.command.service.AccountCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

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
    public AccountTokenResponse refreshAccessToken(@RequestBody AccountAccessTokenRequest request)  {
        return commandService.refreshToken(request);
    }
}

