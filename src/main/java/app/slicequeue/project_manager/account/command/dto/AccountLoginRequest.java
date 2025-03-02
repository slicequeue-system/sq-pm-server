package app.slicequeue.project_manager.account.command.dto;

import lombok.Getter;

@Getter
public class AccountLoginRequest {

    private final String email;

    private final String secretKey;

    public AccountLoginRequest(String email, String secretKey) {
        this.email = email;
        this.secretKey = secretKey;
    }
}
