package app.slicequeue.project_manager.application.project.query.dto;

import app.slicequeue.project_manager.domain.account.model.Account;
import app.slicequeue.project_manager.domain.project.model.Project;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.Instant;

/**
 * ProjectListItemResponse, 프로젝트 생성 요청
 */
@Getter
public class ProjectListItemResponse {

    /**
     * 프로젝트 일련번호
     */
    private long id;
    /**
     * 프로젝트명
     */
    private String name;
    /**
     * 프로젝트 코드
     */
    private String code;
    /**
     * 프로젝트 시작일시 (ISO-8601)
     */
    private Instant startAt;
    /**
     * 프로젝트 수정일시 (ISO-8601)s
     */
    private Instant updatedAt;
    /**
     * 작성자 사용자닉네임
     */
    private String writerNickname;
    /**
     * 프로젝트 생성일시 (ISO-8601)
     */
    private Instant createdAt;
    /**
     * 프로젝트 종료일시 (ISO-8601) - 없는 경우 시작 일시로 부터 +6 개월
     */
    private Instant endAt;

    @Builder
    public ProjectListItemResponse(long id, String name, String code, Instant startAt, Instant endAt,
                                   String writerNickname, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.startAt = startAt;
        this.endAt = endAt;
        this.writerNickname = writerNickname;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ProjectListItemResponse(Project project, Account account) {
        this(project.getId(), project.getName(), project.getCode(), project.getStartAt(), project.getEndAt(),
                account.getNickname(), project.getCreatedAt(), project.getUpdatedAt());
    }
}
