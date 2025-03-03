package app.slicequeue.project_manager.infrastructure.project.repository;

import app.slicequeue.project_manager.application.project.query.dto.ProjectDetailResponse;
import app.slicequeue.project_manager.application.project.query.dto.ProjectListItemResponse;
import app.slicequeue.project_manager.domain.project.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ProjectQueryRepository extends JpaRepository<Project, Long> {

    @Query("""
            select new app.slicequeue.project_manager.application.project.query.dto.ProjectListItemResponse(p, a)
            from Project p inner join Account a on p.accountId = a.id
            """)
    Page<ProjectListItemResponse> findPage(Pageable pageable);

    @Query("""
            select new app.slicequeue.project_manager.application.project.query.dto.ProjectDetailResponse(p, a)
            from Project p inner join Account a on p.accountId = a.id
            where p.id = :projectId
            """)
    Optional<ProjectDetailResponse> findDetailResponseById(@Param("projectId") Long projectId);
}
