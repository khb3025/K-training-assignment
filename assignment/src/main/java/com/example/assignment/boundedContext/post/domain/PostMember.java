package com.example.assignment.boundedContext.post.domain;

import com.example.assignment.share.member.domain.ReplicaMember;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "POST_POST_MEMBER")
public class PostMember extends ReplicaMember {
    public PostMember(
        long id,
        LocalDateTime createdDate,
        LocalDateTime modifyDate,
        String username,
        String nickname,
        String password,
        int activityScore
    ){
        super(
                id,
                createdDate,
                modifyDate,
                username,
                nickname,
                "",
                activityScore
        );
    }
}
