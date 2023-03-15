package com.example.commhere.entity;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@EntityListeners(value = { AuditingEntityListener.class })
@MappedSuperclass
@Getter
abstract class Base {

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "deleted_yn")
    private String deletedYN;

    /**
     * insert 되기 전 실행되는 로직.(persist 되기 전)
     */
    @PrePersist
    public void prePersist(){
        this.deletedYN = this.deletedYN == null ? "N" : this.deletedYN;
    }
}
