package app.slicequeue.project_manager.project.dto;

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
    public ProjectListItemResponse(long id, String name, String code, Instant startAt, Instant updatedAt, String writerNickname, Instant createdAt, Instant endAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.startAt = startAt;
        this.updatedAt = updatedAt;
        this.writerNickname = writerNickname;
        this.createdAt = createdAt;
        this.endAt = endAt;
    }
}
