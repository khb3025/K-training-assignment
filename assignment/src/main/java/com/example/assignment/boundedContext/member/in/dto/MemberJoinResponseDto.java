package com.example.assignment.boundedContext.member.in.dto;

import com.example.assignment.boundedContext.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberJoinResponseDto {
    private long id;
    private String username;
    private String nickname;
    private LocalDateTime createDate;

    public MemberJoinResponseDto(
            Member member
    ){
        this.id = member.getId();
        this.username = member.getUsername();
        this.nickname = member.getNickname();
        this.createDate = member.getCreateDate();
    }
}
