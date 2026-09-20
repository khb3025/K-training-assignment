package com.example.assignment.global.jpa.entity;


import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// Id는 수동 주입
// 생성일, 수정일은 자동 주입 추상 클래스
@MappedSuperclass
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseManualIdAndTime extends BaseEntity{

    @Id
    private long id;
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modifyDate;

}
