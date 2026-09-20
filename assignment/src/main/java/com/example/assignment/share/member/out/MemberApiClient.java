package com.example.assignment.share.member.out;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MemberApiClient {
    private RestClient restClient;

    public MemberApiClient() {
        this.restClient = RestClient.builder()
            .baseUrl("http://localhost:8080/api/v1/member")
            .build();
    }

    public String getSecureTip(){
        return restClient.get()
            .uri("/secure-tip")
            .retrieve()
            .body(String.class);
    }

}
