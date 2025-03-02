// ProjectDetailResponse.java

package app.slicequeue.project_manager.project.query.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.Instant;

/**
 * ProjectDetailResponse, 프로젝트 생성 요청
 */
@Getter
public class ProjectDetailResponse {

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
     * 프로젝트 종료일시 (ISO-8601) - 없는 경우 시작 일시로 부터 +6 개월
     */
    private Instant endAt;
    /**
     * 프로젝트 메모
     */
    private String memo;
    /**
     * 프로젝트 수정일시 (ISO-8601)
     */
    private Instant updatedAt;
    /**
     * 프로젝트 생성일시 (ISO-8601)
     */
    private Instant createdAt;
    /**
     * 작성자 정보
     */
    private Writer writer;

    @Builder
    public ProjectDetailResponse(long id, String name, String code, Instant startAt, Instant endAt, String memo, Instant updatedAt, Instant createdAt, Writer writer) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.startAt = startAt;
        this.endAt = endAt;
        this.memo = memo;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.writer = writer;
    }

    /**
     * 작성자 정보
     */
    @Data
    public static class Writer {

        /**
         * 작성자 사용자일련번호
         */
        private long userId;
        /**
         * 작성자 사용자닉네임
         */
        private String nickname;

        @Builder
        public Writer(long userId, String nickname) {
            this.userId = userId;
            this.nickname = nickname;
        }
    }

}
