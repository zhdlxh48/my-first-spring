package com.zhdlxh48.hellospring.repository;

import com.zhdlxh48.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // JPQL: select m from Member m where m.name = ?
    // findBy[Name] 라고 작성하면 Name 을 찾는 JPQL 를 자동으로 작성한다
    // 복잡한 동적 쿼리는 Querydsl 이라는 라이브러리를 사용하여 처리
    @Override
    Optional<Member> findByName(String name);
}
