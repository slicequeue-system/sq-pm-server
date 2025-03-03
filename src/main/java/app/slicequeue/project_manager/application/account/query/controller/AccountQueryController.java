package app.slicequeue.project_manager.application.account.query.controller;

import app.slicequeue.project_manager.application.account.query.dto.AccountProfileResponse;
import app.slicequeue.project_manager.application.account.query.service.AccountQueryService;
import app.slicequeue.project_manager.common.dto.CommonResponse;
import app.slicequeue.project_manager.domain.account.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class AccountQueryController {

    private final AccountQueryService accountQueryService;

    @GetMapping("/me")
    public CommonResponse<AccountProfileResponse> readProfile(@AuthenticationPrincipal Account account) {
        return CommonResponse.success(accountQueryService.getAccountProfile(account));
    }

}
