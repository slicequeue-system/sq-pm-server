package app.slicequeue.project_manager.domain.account.model;

import app.slicequeue.project_manager.common.base.BaseTimeEntity;
import app.slicequeue.project_manager.config.security.JwtUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static app.slicequeue.project_manager.common.CommonConstants.ValidMessage.POSTFIX_NOT_BLANK;

@Entity
@Table(name = "account")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "email" + POSTFIX_NOT_BLANK)
    private String email;
    @NotBlank(message = "pwd" + POSTFIX_NOT_BLANK)
    private String pwd; // 단방향 암호화된 비밀번호 저장
    private String nickname;
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID refreshToken;
    private Instant refreshTokenExpiredAt;


    @Builder(builderClassName = "mockBuilder", builderMethodName = "mockBuilder")
    private Account(Long id, String email, String pwd, String nickname, Instant createdAt, Instant updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.email = email;
        this.pwd = pwd;
        this.nickname = nickname;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getPassword() {
        return pwd;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void generateRefreshToken(JwtUtil.JwtTokenResult jwtTokenResult) {
        this.refreshToken = UUID.randomUUID();
        this.refreshTokenExpiredAt = jwtTokenResult.expiredDate().toInstant().plus(60, ChronoUnit.DAYS);
    }

    public void encodePwd(PasswordEncoder passwordEncoder) {
        this.pwd = passwordEncoder.encode(this.pwd);
    }

    public boolean matchPassword(String rawPwd, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(rawPwd, this.pwd);
    }
}
