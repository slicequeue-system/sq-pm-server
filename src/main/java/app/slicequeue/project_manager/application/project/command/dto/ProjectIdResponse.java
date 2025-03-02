package app.slicequeue.project_manager.application.project.command.dto;

import org.springframework.util.Assert;

import static app.slicequeue.project_manager.common.CommonConstants.Message.VALID_NOT_NULL;


/**
 * ProjectIdResponse
 */
@lombok.Data
public class ProjectIdResponse {
    /**
     * 프로젝트 일련번호
     */
    private long id;

    private ProjectIdResponse(Long id) {
        Assert.notNull(id, VALID_NOT_NULL.message("id"));
        this.id = id;
    }

    public static ProjectIdResponse id(Long id) {
        return new ProjectIdResponse(id);
    }
}
