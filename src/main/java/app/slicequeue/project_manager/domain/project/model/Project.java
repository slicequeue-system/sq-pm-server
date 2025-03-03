package app.slicequeue.project_manager.domain.project.model;

import app.slicequeue.project_manager.application.project.command.dto.ProjectCreateRequest;
import app.slicequeue.project_manager.application.project.command.dto.ProjectUpdateRequest;
import app.slicequeue.project_manager.common.base.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.Where;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

import static app.slicequeue.project_manager.common.CommonConstants.ValidMessage.POSTFIX_NOT_BLANK;
import static app.slicequeue.project_manager.common.CommonConstants.ValidMessage.POSTFIX_NOT_NULL;

@Entity
@Table(name = "project", indexes = {
        @Index(name = "idx_prj_account_id_start_at_end_at", columnList = "account_id, start_at, end_at"),
        @Index(name = "unq_prj_code", columnList = "code", unique = true)
})
@SQLRestriction("deleted_at IS NULL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project extends BaseTimeEntity {

    private static final Duration DEFAULT_DURATION_END = Duration.of(180, ChronoUnit.DAYS);

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

    @Builder(builderClassName = "builder", builderMethodName = "mockBuilder")
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

    public static Project create(ProjectCreateRequest request, Long accountId) {
        Project project = new Project();
        project.name = request.getName();
        project.code = request.getCode();
        project.accountId = accountId;
        project.startAt = checkStartAtValue(request.getStartAt());
        project.endAt = checkEndAtValueAfterStartAt(request.getEndAt(), project.startAt);
        project.memo = request.getMemo();
        return project;
    }

    private static Instant checkStartAtValue(Instant startAt) {
        Instant now = Instant.now();
        if (startAt == null) {
            return now;
        }

        if (startAt.isBefore(now)) {
            return now;
        }
        return startAt;
    }

    private static Instant checkEndAtValueAfterStartAt(Instant endAt, Instant startAt) {
        if (endAt == null) {
            return startAt.plus(DEFAULT_DURATION_END);
        }

        if (startAt.isAfter(endAt) || startAt.equals(endAt)) {
            throw new ValidationException("endAt은 startAt보다 이후여야 합니다."); // 메시지 추가
        }
        if (Duration.between(startAt, endAt).toHours() < 24) {
            throw new ValidationException("endAt은 startAt 이후 최소 24시간 이상이어야 합니다."); // 메시지 추가
        }
        return endAt;
    }

    public void update(ProjectUpdateRequest request) {
        this.name = request.getName();
        this.startAt = checkStartAtValue(request.getStartAt());
        this.endAt = checkEndAtValueAfterStartAt(request.getEndAt(), this.startAt);
        this.memo = request.getMemo();
        super.nowUpdateAt();
    }

    public void delete() {
        this.nowDeletedAt();
    }
}
