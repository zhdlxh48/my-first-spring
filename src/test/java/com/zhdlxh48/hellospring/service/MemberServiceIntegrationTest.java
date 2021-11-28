 package com.zhdlxh48.hellospring.service;

import com.zhdlxh48.hellospring.domain.Member;
import com.zhdlxh48.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// TODO: DB 의 Transaction 에 대해 알아보기
@SpringBootTest // Spring 컨테이너 안에서 테스트를 실행한다
@Transactional // 테스트 시작 전 트랜젝션을 하고 마지막에 Rollback 을 실행해 기록을 남기지 않는다
class MemberServiceIntegrationTest {
    @Autowired MemberService service;
    @Autowired MemberRepository repository;

    @Test
    public void join() {
        Member member = new Member();
        member.setName("spring");

        Long saveId = service.join(member);

        Member findMember = service.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void validateDuplicateMember() {
        Member member1 = new Member();
        member1.setName("hello");
        Member member2 = new Member();
        member2.setName("hello");

        IllegalStateException err = assertThrows(IllegalStateException.class, () -> {
            service.join(member1);
            service.join(member2);
        });

        assertThat(err.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}