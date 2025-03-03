package app.slicequeue.project_manager.infrastructure.project.repository;

import app.slicequeue.project_manager.application.project.query.dto.ProjectListItemResponse;
import app.slicequeue.project_manager.domain.project.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ProjectQueryRepository extends JpaRepository<Project, Long> {

    @Query("""
            select new app.slicequeue.project_manager.application.project.query.dto.ProjectListItemResponse(p, a)
            from Project p inner join Account a on p.accountId = a.id
            """)
    Page<ProjectListItemResponse> findPage(Pageable pageable);

//    @Query("""
//            """)
//    ProjectDetailResponse findDetailResponseById(Long id)
}
