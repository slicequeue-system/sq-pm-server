package app.slicequeue.project_manager.domain.account.model;

import app.slicequeue.project_manager.common.base.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

import static app.slicequeue.project_manager.common.CommonConstants.ValidMessage.POSTFIX_NOT_BLANK;

@Entity
@Table(name = "account")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseTimeEntity {

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
}
