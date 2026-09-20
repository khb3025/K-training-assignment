package com.example.assignment.share.member.domain;

import com.example.assignment.global.jpa.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor
@Getter
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
