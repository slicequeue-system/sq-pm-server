package app.slicequeue.project_manager.application.account.query.service;

import app.slicequeue.project_manager.application.account.query.dto.AccountProfileResponse;
import app.slicequeue.project_manager.domain.account.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountQueryService {

    public AccountProfileResponse getAccountProfile(Account account) {
        return AccountProfileResponse.from(account);
    }
}
