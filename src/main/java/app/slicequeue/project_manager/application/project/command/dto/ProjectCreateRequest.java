package app.slicequeue.project_manager.application.project.command.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.Instant;

import static app.slicequeue.project_manager.common.CommonConstants.ValidMessage.POSTFIX_NOT_BLANK;
import static app.slicequeue.project_manager.common.CommonConstants.ValidMessage.POSTFIX_NOT_NULL;

/**
 * ProjectCreateRequest, 프로젝트 생성 요청
 */
@Getter
public class ProjectCreateRequest {
    /**
     * 프로젝트명
     */
    @NotBlank(message = "code" + POSTFIX_NOT_BLANK)
    @Max(128)
    private String name;
    /**
     * 프로젝트 코드
     */
    @NotBlank(message = "code" + POSTFIX_NOT_BLANK)
    @Max(64)
    private String code;
    /**
     * 프로젝트 메모
     */
    private String memo;
    /**
     * 프로젝트 시작일시 (ISO-8601)
     */
    @NotNull(message = "startAt" + POSTFIX_NOT_NULL)
    private Instant startAt;
    /**
     * 프로젝트 종료일시 (ISO-8601) - 없는 경우 시작 일시로 부터 +6 개월
     */
    private Instant endAt;

}
