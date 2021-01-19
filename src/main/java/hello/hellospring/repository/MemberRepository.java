package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원 저장 --> 저장된 회원 반환
    //Optional이란? java8에 들어간 기능. 없으면 null로 반환할 것인데, 요즘은 Optional로 감싸서 반환하는 것을 선호함.
    Optional<Member> findById(Long id); //id로 회원을 찾는 기능.
    Optional<Member> findByName(String name);
    List<Member> findAll();//모든 회원 리스트 반환
}
