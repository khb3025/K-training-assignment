package com.example.assignment.boundedContext.member.app;

import com.example.assignment.boundedContext.member.domain.Member;
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
    public Optional<Member> join(
        String username,
        String nickname,
        String password
    ){
        return memberUseCase.join(username, nickname, password);
    }

    public Optional<Member> findByUsername(String username) {
        return memberUseCase.findByUsername(username);
    }

    public Optional<Member> findById(long id) {
        return memberUseCase.findById(id);
    }
}
