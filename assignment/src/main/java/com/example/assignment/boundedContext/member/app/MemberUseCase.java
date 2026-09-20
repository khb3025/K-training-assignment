package com.example.assignment.boundedContext.member.app;

import com.example.assignment.boundedContext.member.domain.Member;
import com.example.assignment.boundedContext.member.out.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberUseCase {

    private final MemberRepository memberRepository;

    public Optional<Member> join(
            String username,
            String nickname,
            String password
    ) {
        Member member = new Member(
                username,
                nickname,
                password,
                0
        );

        memberRepository.save(member);
        return Optional.of(member);
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Optional<Member> findById(long id) {
        return memberRepository.findById(id);
    }

}
