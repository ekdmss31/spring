package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> ,MemberRepository{//다중상속
    
    //interface만 있는데, 이 SpringDataJpa가 JpaRepository를 받고 있으면 구현체를 자동으로 만들어서 자동으로 등록해줌. 
    //우리는 그것을 가져다 쓰기만 하면 됨.<--SpringConfig에서

    //JPQL에서는 이를 select m from Member m where m.name = ? 으로 불러올 수 있음.
    @Override
    Optional<Member> findByName(String name);
}
