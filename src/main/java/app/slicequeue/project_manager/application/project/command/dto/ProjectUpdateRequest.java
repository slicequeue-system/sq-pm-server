package app.slicequeue.project_manager.application.project.command.dto;

import java.time.Instant;

/**
 * ProjectUpdeteRequest, 프로젝트 생성 요청
 */
@lombok.Data
public class ProjectUpdateRequest {
    /**
     * 프로젝트 메모
     */
    private String memo;
    /**
     * 프로젝트명
     */
    private String name;
    /**
     * 프로젝트 시작일시 (ISO-8601)
     */
    private Instant startAt;
    /**
     * 프로젝트 종료일시 (ISO-8601) - 없는 경우 시작 일시로 부터 +6 개월
     */
    private Instant endAt;
}
