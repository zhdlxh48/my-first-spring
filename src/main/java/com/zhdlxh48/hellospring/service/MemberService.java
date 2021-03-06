package com.zhdlxh48.hellospring.service;

import com.zhdlxh48.hellospring.domain.Member;
import com.zhdlxh48.hellospring.repository.MemberRepository;
import com.zhdlxh48.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 서비스 로직과 비즈니스 로직은 이름부터 각각의 성격을 띄도록 확실히 구분해야 함
// JPA 로 데이터를 조회 할 때는 반드시 Transactional 어노테이션을 써줘야 함
// 특정 함수에서만 쓴다면 함수에만 어노테이션을 써주어도 됨
@Transactional
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        validateDuplicateMember(member);

        repository.save(member);
        return member.getId();
    }

    /**
     * 전체회원 조회
     * @return
     */
    public List<Member> findMembers() {
        return repository.findAll();
    }

    /**
     * 특정회원 조회
     * @param memberId
     * @return
     */
    public Optional<Member> findOne(Long memberId) {
        return repository.findById(memberId);
    }

    /**
     * 중복회원 가입방지
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        repository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }


}
