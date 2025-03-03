package app.slicequeue.project_manager.domain.project.service;

import app.slicequeue.project_manager.application.project.command.dto.ProjectCreateRequest;
import app.slicequeue.project_manager.domain.account.model.Account;
import app.slicequeue.project_manager.domain.project.model.Project;
import app.slicequeue.project_manager.infrastructure.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProjectService {

    private final ProjectRepository projectRepository;

    public Project create(ProjectCreateRequest request, Account account) {
        Project project = Project.create(request, account.getId());
        return projectRepository.save(project);
    }
}
