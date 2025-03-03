package app.slicequeue.project_manager.application.project.query.controller;

import app.slicequeue.project_manager.application.project.query.dto.ProjectListItemResponse;
import app.slicequeue.project_manager.application.project.query.service.ProjectQueryService;
import app.slicequeue.project_manager.common.dto.CommonResponse;
import app.slicequeue.project_manager.application.project.query.dto.ProjectDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/v1/projects")
@RequiredArgsConstructor
public class ProjectQueryController {

    private final ProjectQueryService projectQueryService;

    @GetMapping
    public CommonResponse<Page<ProjectListItemResponse>> readAll(@PageableDefault(size = 15, sort = "createdAt",
            direction = Sort.Direction.DESC) Pageable pageable) {
        return CommonResponse.success(projectQueryService.readAll(pageable));
    }


    @GetMapping("/{projectId}")
    public CommonResponse<ProjectDetailResponse> read(@PathVariable Long projectId) {
        // TODO 구현
        return CommonResponse.success(ProjectDetailResponse.builder()
                .id(123L)
                .name("JamCar")
                .code("SQ-JC")
                .startAt(Instant.parse("2025-03-01T00:00:00.0Z"))
                .endAt(Instant.parse("2026-01-01T00:00:00.0Z"))
                .createdAt(Instant.parse("2025-02-28T13:50:00.0Z"))
                .updatedAt(Instant.parse("2025-02-28T14:05:00.0Z"))
                .writer(ProjectDetailResponse.Writer.builder()
                        .userId(10L)
                        .nickname("별땜")
                        .build())
                .build());
    }
}
