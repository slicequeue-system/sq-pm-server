package app.slicequeue.project_manager.application.project.query.controller;

import app.slicequeue.project_manager.application.project.query.dto.ProjectListItemResponse;
import app.slicequeue.project_manager.application.project.query.service.ProjectQueryService;
import app.slicequeue.project_manager.common.dto.CommonResponse;
import app.slicequeue.project_manager.application.project.query.dto.ProjectDetailResponse;
import app.slicequeue.project_manager.domain.account.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/projects")
@RequiredArgsConstructor
public class ProjectQueryController {

    private final ProjectQueryService projectQueryService;

    @GetMapping
    public CommonResponse<Page<ProjectListItemResponse>> readAll(@PageableDefault(size = 15, sort = "createdAt",
            direction = Sort.Direction.DESC) Pageable pageable) {
        return CommonResponse.success(projectQueryService.readAllProjects(pageable));
    }


    @GetMapping("/{projectId}")
    public CommonResponse<ProjectDetailResponse> read(
            @AuthenticationPrincipal Account account,
            @PathVariable Long projectId) {
        return CommonResponse.success(projectQueryService.readProject(projectId, account));
    }
}
