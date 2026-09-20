package com.example.assignment.share.member.event;

import com.example.assignment.share.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class MemberModifiedEvent {
    private final MemberDto memberDto;
}
