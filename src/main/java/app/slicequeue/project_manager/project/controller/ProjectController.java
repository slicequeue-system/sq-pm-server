package app.slicequeue.project_manager.project.controller;

import app.slicequeue.project_manager.common.dto.CommonResponse;
import app.slicequeue.project_manager.project.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

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

    @GetMapping
    public CommonResponse<List<ProjectListItemResponse>> readAll(Pageable pageable) {
        // TODO 구현
        return CommonResponse.success(List.of(ProjectListItemResponse.builder()
                .id(123L)
                .name("JamCar")
                .code("SQ-JC")
                .startAt(Instant.parse("2025-03-01T00:00:00.0Z"))
                .endAt(Instant.parse("2026-01-01T00:00:00.0Z"))
                .createdAt(Instant.parse("2025-02-28T13:50:00.0Z"))
                .updatedAt(Instant.parse("2025-02-28T14:05:00.0Z"))
                .writerNickname("별땜")
                .build()));
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
