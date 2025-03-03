package app.slicequeue.project_manager.common.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class BaseTimeEntity {

    @CreationTimestamp
    @Comment("생성일시")
    @Column(updatable = false)
    private final Instant createdAt;

    @UpdateTimestamp
    @Comment("수정일시")
    @Column
    private Instant updatedAt;

    @Comment("삭제일시")
    @Column
    private Instant deletedAt;

    protected BaseTimeEntity() {
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    protected BaseTimeEntity(Instant createdAt, Instant updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    protected void nowUpdateAt() {
        this.updatedAt = Instant.now();
    }

    protected void nowDeletedAt() {
        this.deletedAt = Instant.now();
        this.updatedAt = this.deletedAt;
    }
}
