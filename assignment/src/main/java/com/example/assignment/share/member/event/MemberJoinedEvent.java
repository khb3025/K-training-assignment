package com.example.assignment.share.member.event;

import com.example.assignment.share.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberJoinedEvent {
    private final MemberDto memberDto;
}
