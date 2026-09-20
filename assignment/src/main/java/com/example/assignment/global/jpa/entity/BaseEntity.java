package com.example.assignment.global.jpa.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@MappedSuperclass
@NoArgsConstructor
@Getter
public abstract class BaseEntity {
    public abstract long getId();
    public abstract LocalDateTime getCreateDate();
    public abstract LocalDateTime getModifyDate();
    public String getModelTypeCode() {
        return this.getClass().getSimpleName();
    }

}
