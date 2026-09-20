package com.example.assignment.share.member.domain;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@NoArgsConstructor
public class ReplicaMember extends BaseMember{
    @Id
    private long id;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public ReplicaMember(
        long id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        String username,
        String nickname,
        String password,
        int activityScore
    ){
        super(username, nickname, password, activityScore);
        this.id = id;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    };
}
