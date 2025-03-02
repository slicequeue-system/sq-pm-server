package app.slicequeue.project_manager.domain.account.model;

import app.slicequeue.project_manager.common.base.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    private String pwd;
    private String nickname;


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
        return email.toString();
    }

    public String getPassword() {
        return pwd.toString();
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
}
