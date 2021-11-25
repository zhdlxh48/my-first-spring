package com.zhdlxh48.hellospring;

import com.zhdlxh48.hellospring.repository.MemberRepository;
import com.zhdlxh48.hellospring.repository.MemoryMemberRepository;
import com.zhdlxh48.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
