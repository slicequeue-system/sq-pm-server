package app.slicequeue.project_manager.domain.project.model;

import app.slicequeue.project_manager.common.base.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

import static app.slicequeue.project_manager.common.CommonConstants.ValidMessage.POSTFIX_NOT_BLANK;
import static app.slicequeue.project_manager.common.CommonConstants.ValidMessage.POSTFIX_NOT_NULL;

@Entity
@Table(name = "project", indexes = {
        @Index(name = "idx_prj_account_id_start_at_end_at", columnList = "account_id, start_at, end_at"),
        @Index(name = "unq_prj_code", columnList = "code", unique = true)
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "name" + POSTFIX_NOT_BLANK)
    private String name;
    @NotBlank(message = "code" + POSTFIX_NOT_BLANK)
    @Column(unique = true)
    private String code;
    @NotNull(message = "accountId" + POSTFIX_NOT_NULL)
    private Long accountId;
    @NotNull(message = "startAt" + POSTFIX_NOT_NULL)
    private Instant startAt;
    @NotNull(message = "endAt" + POSTFIX_NOT_NULL)
    private Instant endAt;
    private String memo;

    @Builder(builderClassName = "mockBuilder", builderMethodName = "mockBuilder")
    private Project(Instant createdAt, Instant updatedAt, Long id, String name, String code, Long accountId,
              Instant startAt, Instant endAt, String memo) {
        super(createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.code = code;
        this.accountId = accountId;
        this.startAt = startAt;
        this.endAt = endAt;
        this.memo = memo;
    }
}
