package com.zhdlxh48.hellospring;

import com.zhdlxh48.hellospring.repository.JdbcTemplateMemberRepository;
import com.zhdlxh48.hellospring.repository.JpaMemberRepository;
import com.zhdlxh48.hellospring.repository.MemberRepository;
import com.zhdlxh48.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    // @PersistenceContext
    // TODO: PersistanceContext 에 대해 알아보기
    private DataSource dataSource;
    private EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
