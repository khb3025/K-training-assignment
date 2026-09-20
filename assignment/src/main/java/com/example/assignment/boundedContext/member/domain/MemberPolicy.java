package com.example.assignment.boundedContext.member.domain;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class MemberPolicy {

    private final int PASSWORD_CHANGE_LIMIT = 90;

    public int getPasswordChangeLimit() {
        return PASSWORD_CHANGE_LIMIT;
    }

    public Duration getPasswordChangeLimitDuration() {
        return Duration.ofDays(PASSWORD_CHANGE_LIMIT);
    }

    public boolean isNeedToPasswordChange(LocalDateTime lastPasswordChangeDate) {
        return lastPasswordChangeDate.plusDays(PASSWORD_CHANGE_LIMIT).isBefore(LocalDateTime.now());
    }


}
