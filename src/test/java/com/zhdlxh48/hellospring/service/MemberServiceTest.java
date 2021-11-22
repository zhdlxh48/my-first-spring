package com.zhdlxh48.hellospring.service;

import com.zhdlxh48.hellospring.domain.Member;
import com.zhdlxh48.hellospring.repository.MemberRepository;
import com.zhdlxh48.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

// Given && When && Then
class MemberServiceTest {
    MemberService service;
    MemoryMemberRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new MemoryMemberRepository();
        service = new MemberService(repository);
    }
    
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void join() {
        Member member = new Member();
        member.setName("spring");

        Long saveId = service.join(member);

        Member findMember = service.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void findMembers() {
    }

    @Test
    public void findOne() {
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
/*
        service.join(member1);
        try {
            service.join(member2);
            Assertions.fail("중복된 회원에 대한 예외가 발생하지 않음.");
        } catch (IllegalStateException e) {
            assertThat(e.getClass()).isEqualTo(IllegalStateException.class);
        }
*/
    }
}