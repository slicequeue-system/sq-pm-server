package app.slicequeue.project_manager.domain.project.service;

import app.slicequeue.project_manager.common.exception.NotFoundException;
import app.slicequeue.project_manager.domain.account.model.Account;
import app.slicequeue.project_manager.domain.project.model.Project;
import app.slicequeue.project_manager.infrastructure.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProjectService {

    private final ProjectRepository projectRepository;

    public Project delete(Long projectId, Account account) {
        Project project = projectRepository.findByIdAndAccountId(projectId, account.getId()).orElseThrow(() -> new NotFoundException("프로젝트 데이터를 찾을 수 없습니다"));
        project.delete();
        return projectRepository.saveAndFlush(project);
    }

}
