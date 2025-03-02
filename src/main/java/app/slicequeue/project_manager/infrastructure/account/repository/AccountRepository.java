package app.slicequeue.project_manager.infrastructure.account.repository;

import app.slicequeue.project_manager.domain.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
