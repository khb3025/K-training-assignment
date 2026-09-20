package com.example.assignment.boundedContext.member.app;

import com.example.assignment.boundedContext.member.domain.Member;
import com.example.assignment.boundedContext.member.domain.MemberPolicy;
import com.example.assignment.boundedContext.member.in.dto.MemberJoinResponseDto;
import com.example.assignment.boundedContext.member.out.MemberRepository;
import com.example.assignment.global.RsData.RsData;
import com.example.assignment.global.exception.DomainException;
import com.example.assignment.global.publisher.EventPublisher;
import com.example.assignment.share.member.dto.MemberDto;
import com.example.assignment.share.member.event.MemberJoinedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberUseCase {

    private final MemberRepository memberRepository;
    private final EventPublisher eventPublisher;
    private final MemberPolicy memberPolicy;

    public RsData<MemberJoinResponseDto> join(
            String username,
            String nickname,
            String password
    ) {
        try {
            Member member = new Member(
                    username,
                    nickname,
                    password,
                    0
            );

            memberRepository.save(member);
            eventPublisher.publish(new MemberJoinedEvent(new MemberDto(member)));
            MemberJoinResponseDto memberJoinResponseDto = new MemberJoinResponseDto(member);
            return new RsData<MemberJoinResponseDto>("201","%s 님이 회원가입 했습니다.".formatted(username) ,memberJoinResponseDto);

        }catch (DataIntegrityViolationException e){
            throw new DomainException("이미 존재하는 username 입니다.[Unique 제약 위반]");
        }

    }

    public RsData<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username)
                .map(member -> new RsData<>("200", "%s 회원 정보가 조회되었습니다.".formatted(username), member))
                .orElseGet(() -> new RsData<>("404", "회원정보가 조회되지 않습니다.".formatted(username), null));
    }

    public RsData<Member> findById(long id) {
        return memberRepository.findById(id)
                .map(member -> new RsData<>("200", "%s 회원 정보가 조회되었습니다.".formatted(member.getUsername()), member))
                .orElseGet(() -> new RsData<>("404", "회원정보가 조회되지 않습니다.", null));
    }

    public String getSecureTip() {
        return "비밀번호의 유효기간은 %d일 입니다.".formatted(
                memberPolicy.getPasswordChangeLimit());
    }

    public long count() {
        return memberRepository.count();
    }
}
