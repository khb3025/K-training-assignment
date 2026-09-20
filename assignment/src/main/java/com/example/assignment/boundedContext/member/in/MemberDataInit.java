package com.example.assignment.boundedContext.member.in;

import com.example.assignment.boundedContext.member.app.MemberFacade;
import com.example.assignment.boundedContext.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@Slf4j
public class MemberDataInit {
    private final MemberDataInit self;
    private final MemberFacade memberFacade;

    public MemberDataInit(
            @Lazy MemberDataInit self,
            MemberFacade memberFacade
    ) {
        this.self = self;
        this.memberFacade = memberFacade;
    }

    @Bean
    @Order(1)
    public ApplicationRunner memberInitDataRunner() {
        return args -> {
            self.makeBaseMember();
        };
    }
    @Transactional
    public void makeBaseMember(){
        Member member1 = memberFacade.join("system", "시스템", "1234").get();
        Member member2 = memberFacade.join("holding", "홀딩", "1234").get();
        Member member3 = memberFacade.join("admin", "관리자", "1234").get();
        Member member4 = memberFacade.join("user1", "유저1", "1234").get();
        Member member5 = memberFacade.join("user2", "유저2", "1234").get();
        Member member6 = memberFacade.join("user3", "유저3", "1234").get();
    }

}
