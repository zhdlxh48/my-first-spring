package com.zhdlxh48.hellospring.service;

import com.zhdlxh48.hellospring.domain.Member;
import com.zhdlxh48.hellospring.repository.MemberRepository;
import com.zhdlxh48.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository repository = new MemoryMemberRepository();

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
