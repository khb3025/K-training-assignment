package com.example.assignment.boundedContext.member.app;

import com.example.assignment.boundedContext.member.domain.Member;
import com.example.assignment.boundedContext.member.in.dto.MemberJoinResponseDto;
import com.example.assignment.global.RsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberFacade {

    private final MemberUseCase memberUseCase;

    @Transactional
    public RsData<MemberJoinResponseDto> join(
        String username,
        String nickname,
        String password
    ){
        return memberUseCase.join(username, nickname, password);
    }

    public RsData<Member> findByUsername(String username) {
        return memberUseCase.findByUsername(username);
    }

    public RsData<Member> findById(long id) {
        return memberUseCase.findById(id);
    }
    public String getSecureTip(){
        return memberUseCase.getSecureTip();
    }

    public long count(){
        return memberUseCase.count();
    }
}
