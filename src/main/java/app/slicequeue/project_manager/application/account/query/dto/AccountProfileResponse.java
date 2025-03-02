package app.slicequeue.project_manager.application.account.query.dto;

import app.slicequeue.project_manager.domain.account.model.Account;
import lombok.Getter;

import java.time.Instant;

@Getter
public class AccountProfileResponse {

    private Long userId;
    private String email;
    private String nickname;
    private Instant createdAt;

    public static AccountProfileResponse from(Account account) {
        AccountProfileResponse response = new AccountProfileResponse();
        response.userId = account.getId();
        response.email = account.getEmail();
        response.nickname = account.getNickname();
        response.createdAt = account.getCreatedAt();
        return response;
    }
}
