package app.slicequeue.project_manager.infrastructure.project.repository;

import app.slicequeue.project_manager.domain.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByIdAndAccountId(Long id, Long accountId);

}
