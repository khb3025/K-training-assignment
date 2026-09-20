package com.example.assignment.boundedContext.member.domain;

import com.example.assignment.share.member.domain.SourceMember;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "MEMBER_MEMBER")
public class Member extends SourceMember {
    public Member(
            String username,
            String nickname,
            String password,
            int activityScore
    ){
        super(username, nickname, password, activityScore);
    }
}
