package com.example.assignment.boundedContext.member.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberJoinRequestDto {
    private final String username;
    private final String nickname;
    private final String password;
}
