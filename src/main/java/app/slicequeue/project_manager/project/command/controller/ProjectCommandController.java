package app.slicequeue.project_manager.project.command.controller;

import app.slicequeue.project_manager.common.dto.CommonResponse;
import app.slicequeue.project_manager.project.command.dto.ProjectCreateRequest;
import app.slicequeue.project_manager.project.command.dto.ProjectIdResponse;
import app.slicequeue.project_manager.project.command.dto.ProjectUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/projects")
@RequiredArgsConstructor
public class ProjectCommandController {

    @PostMapping
    public CommonResponse<ProjectIdResponse> create(@RequestBody ProjectCreateRequest request) {
        // TODO 구현
        return CommonResponse.success(ProjectIdResponse.id(123L));
    }

    @PutMapping("/{projectId}")
    public CommonResponse<ProjectIdResponse> update(@PathVariable Long projectId,
                                                    @RequestBody ProjectUpdateRequest request) {
        // TODO 구현
        return CommonResponse.success(ProjectIdResponse.id(123L));
    }

    @DeleteMapping("/{projectId}")
    public CommonResponse<ProjectIdResponse> delete(@PathVariable Long projectId) {
        // TODO 구현
        return CommonResponse.success(ProjectIdResponse.id(123L));
    }
}
