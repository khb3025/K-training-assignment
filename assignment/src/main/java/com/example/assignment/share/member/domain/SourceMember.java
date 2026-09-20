package com.example.assignment.share.member.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@MappedSuperclass
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class SourceMember extends BaseMember{

    @Id @GeneratedValue(strategy = IDENTITY)
    private long id;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifyDate;

    public SourceMember(
        String username,
        String nickname,
        String password,
        int activityScore
    ) {
        super(username, nickname, password, activityScore);
    }
}
