package com.cgkim.jpabasic.repository;

import com.cgkim.jpabasic.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
        /*
         * member 가 아닌 id 를 리턴 하는 이유 :
         * '커맨드랑 쿼리를 분리해라'? 라는 원칙에 의해서
         * 저장을 하고 나면 가급적이면 이거는 사이드 이펙트를 일으키는 커맨드성이기 때문에 리턴값을 거의 안만든다
         * 대신 id 정도는 있으면 다시 조회할수있으니까 그래서 id 정도만 조회하는걸로 주로 설계함
         */
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
