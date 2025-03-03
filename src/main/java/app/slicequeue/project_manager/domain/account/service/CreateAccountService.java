package app.slicequeue.project_manager.domain.account.service;

import app.slicequeue.project_manager.application.account.command.dto.AccountRegisterRequest;
import app.slicequeue.project_manager.domain.account.model.Account;
import app.slicequeue.project_manager.infrastructure.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public Account create(AccountRegisterRequest request) {
        Account account = Account.create(request, passwordEncoder);
        return accountRepository.saveAndFlush(account);
    }
}
