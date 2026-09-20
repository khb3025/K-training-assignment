package com.example.assignment.boundedContext.member.in;

import com.example.assignment.boundedContext.member.app.MemberFacade;
import com.example.assignment.boundedContext.member.domain.Member;
import com.example.assignment.boundedContext.member.in.dto.MemberJoinRequestDto;
import com.example.assignment.boundedContext.member.in.dto.MemberJoinResponseDto;
import com.example.assignment.global.RsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class ApiV1MemberController {

    private final MemberFacade memberFacade;

    @GetMapping("/secure-tip")
    public String getSecureTip(){
        return memberFacade.getSecureTip();
    }

    @PostMapping("/join")
    public RsData<MemberJoinResponseDto> join(@RequestBody MemberJoinRequestDto memberJoinRequestDto){
         return memberFacade.join(
             memberJoinRequestDto.getUsername(),
             memberJoinRequestDto.getNickname(),
             memberJoinRequestDto.getPassword()
         );
    }
}
