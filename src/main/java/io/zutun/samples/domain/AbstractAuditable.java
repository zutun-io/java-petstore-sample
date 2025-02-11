package io.zutun.samples.domain;

import jakarta.persistence.*;
import org.springframework.data.domain.Auditable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AbstractAuditable extends AbstractPersistable implements Auditable<String, UUID, LocalDateTime>, Persistable<UUID> {

    @Column(updatable = false)
    protected String createdBy;

    @Temporal(TemporalType.TIMESTAMP) //
    @Column(updatable = false)
    protected LocalDateTime createdDate;

    protected String lastModifiedBy;

    @Temporal(TemporalType.TIMESTAMP) //
    protected LocalDateTime lastModifiedDate;

    @Override
    public Optional<String> getCreatedBy() {
        return Optional.ofNullable(createdBy);
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Optional<LocalDateTime> getCreatedDate() {
        return Optional.ofNullable(createdDate);
    }

    @Override
    public void setCreatedDate(LocalDateTime creationDate) {
        this.createdDate = creationDate;
    }

    @Override
    public Optional<String> getLastModifiedBy() {
        return Optional.ofNullable(lastModifiedBy);
    }

    @Override
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public Optional<LocalDateTime> getLastModifiedDate() {
        return Optional.ofNullable(lastModifiedDate);
    }

    @Override
    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }


}