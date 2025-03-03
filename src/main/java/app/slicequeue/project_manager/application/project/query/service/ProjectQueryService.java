package app.slicequeue.project_manager.application.project.query.service;

import app.slicequeue.project_manager.application.project.query.dto.ProjectListItemResponse;
import app.slicequeue.project_manager.common.exception.BadRequestException;
import app.slicequeue.project_manager.infrastructure.project.repository.ProjectQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectQueryService {

    private final ProjectQueryRepository projectQueryRepository;

    private static final int LIMIT_PAGE_SIZE = 100;
    private static final int LIMIT_PAGE_NUMBER = 10000;

    public Page<ProjectListItemResponse> readAll(Pageable pageable) {
        if (pageable.getPageSize() > LIMIT_PAGE_SIZE) {
            throw new BadRequestException("잘못된 페이지 크기입니다. 최대 " + LIMIT_PAGE_SIZE);
        }
        if (pageable.getPageNumber() > LIMIT_PAGE_NUMBER) {
            throw new BadRequestException("잘못된 페이지 번호입니다. 최대 " + LIMIT_PAGE_NUMBER);
        }
        return projectQueryRepository.findPage(pageable);
    }
}
