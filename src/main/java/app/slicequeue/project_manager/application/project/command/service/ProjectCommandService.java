package app.slicequeue.project_manager.application.project.command.service;

import app.slicequeue.project_manager.application.project.command.dto.ProjectCreateRequest;
import app.slicequeue.project_manager.application.project.command.dto.ProjectIdResponse;
import app.slicequeue.project_manager.domain.account.model.Account;
import app.slicequeue.project_manager.domain.project.model.Project;
import app.slicequeue.project_manager.domain.project.service.CreateProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectCommandService {

    private final CreateProjectService createProjectService;

    public ProjectIdResponse createProject(ProjectCreateRequest request, Account account) {
        Project project = createProjectService.create(request, account);
        return ProjectIdResponse.id(project.getId());
    }

}
