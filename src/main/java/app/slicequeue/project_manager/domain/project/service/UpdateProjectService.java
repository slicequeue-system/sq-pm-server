package app.slicequeue.project_manager.domain.project.service;

import app.slicequeue.project_manager.application.project.command.dto.ProjectUpdateRequest;
import app.slicequeue.project_manager.common.exception.NotFoundException;
import app.slicequeue.project_manager.domain.account.model.Account;
import app.slicequeue.project_manager.domain.project.model.Project;
import app.slicequeue.project_manager.infrastructure.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProjectService {

    private final ProjectRepository projectRepository;

    public Project update(Long projectId, ProjectUpdateRequest request, Account account) {
        Project project =
                projectRepository.findByIdAndAccountId(projectId, account.getId()).orElseThrow(() -> new NotFoundException("프로젝트 데이터를 찾을 수 없습니다"));
        project.update(request);
        return projectRepository.saveAndFlush(project);
    }
}
