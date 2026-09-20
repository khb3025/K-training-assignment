package com.example.assignment.share.member.domain;

import com.example.assignment.global.jpa.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter(PROTECTED)
public abstract class BaseMember extends BaseEntity {
    @Column(unique = true)
    private String username;
    private String nickname;
    private String password;
    private int activityScore;

    public BaseMember(
            String username,
            String nickname,
            String password,
            int activityScore
    ){
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.activityScore = activityScore;
    }
}
