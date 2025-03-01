package app.slicequeue.project_manager.account.controller;

import app.slicequeue.project_manager.account.dto.AccountAccessTokenRequest;
import app.slicequeue.project_manager.account.dto.AccountLoginRequest;
import app.slicequeue.project_manager.account.dto.AccountTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    @PostMapping("/login")
    public AccountTokenResponse login(@RequestBody AccountLoginRequest request) {
        // TODO 구현
        return getMockAccessToken();
    }

    @PostMapping("/access-token")
    public AccountTokenResponse refreshAccessToken(@RequestBody AccountAccessTokenRequest request)  {
        // TODO 구현
        return getMockAccessToken();
    }

    private static AccountTokenResponse getMockAccessToken() {
        return AccountTokenResponse.of(UUID.randomUUID(), "accessToken", Instant.now().plus(1, ChronoUnit.HOURS));
    }
}

