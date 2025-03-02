package app.slicequeue.project_manager.domain.account.service;

import app.slicequeue.project_manager.application.account.command.dto.AccountLoginRequest;
import app.slicequeue.project_manager.application.account.command.dto.AccountTokenResponse;
import app.slicequeue.project_manager.common.exception.BadRequestException;
import app.slicequeue.project_manager.config.security.JwtUtil;
import app.slicequeue.project_manager.domain.account.model.Account;
import app.slicequeue.project_manager.infrastructure.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginAccountService {

    private final AccountRepository accountRepository;
    private final JwtUtil jwtUtil;

    public AccountTokenResponse login(AccountLoginRequest request) {
        Account account =
                accountRepository.findByEmail(request.getEmail()).orElseThrow(() -> new BadRequestException("아이디 " +
                        "비밀번호를 확인해주세요"));
        if (!account.getPassword().equals(request.getSecretKey())) {
            throw new BadRequestException("아이디 비밀번호를 확인해주세요");
        }
        JwtUtil.JwtTokenResult jwtTokenResult = jwtUtil.generateToken(account.getId(), List.of());
        account.generateRefreshToken(jwtTokenResult);

        return AccountTokenResponse.of(account, jwtTokenResult);
    }
}
