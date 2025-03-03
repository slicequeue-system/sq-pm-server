package app.slicequeue.project_manager.application.project.command.service;

import app.slicequeue.project_manager.application.project.command.dto.ProjectCreateRequest;
import app.slicequeue.project_manager.application.project.command.dto.ProjectIdResponse;
import app.slicequeue.project_manager.application.project.command.dto.ProjectUpdateRequest;
import app.slicequeue.project_manager.domain.account.model.Account;
import app.slicequeue.project_manager.domain.project.model.Project;
import app.slicequeue.project_manager.domain.project.service.CreateProjectService;
import app.slicequeue.project_manager.domain.project.service.DeleteProjectService;
import app.slicequeue.project_manager.domain.project.service.UpdateProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectCommandService {

    private final CreateProjectService createProjectService;
    private final UpdateProjectService updateProjectService;
    private final DeleteProjectService deleteProjectService;

    public ProjectIdResponse createProject(ProjectCreateRequest request, Account account) {
        Project project = createProjectService.create(request, account);
        return ProjectIdResponse.from(project);
    }

    public ProjectIdResponse updateProject(Long projectId, ProjectUpdateRequest request, Account account) {
        Project project = updateProjectService.update(projectId, request, account);
        return ProjectIdResponse.from(project);
    }

    public ProjectIdResponse deleteProject(Long projectId, Account account) {
        Project project = deleteProjectService.delete(projectId, account);
        return ProjectIdResponse.from(project);
    }

}
