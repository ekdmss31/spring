package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional //jpa는 항상Transactional이란게 있어야 함. 데이터를 저장하거나 변경할 때 트랜잭션이 필요함. 회원가입만 할땜나 필요하니까 회원가입 위에 넣어도됨.
public class JpaMemberRepository implements MemberRepository{

    //라이브러리를 다운받으면 springboot가 다 연결을 해줌. 그래서 우리는 인젝션만 하면 됨
    private final EntityManager em; //jpa는 이 EntityManager로 모든게 동작을 함

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //jpa가 insert query를 만들어서 db에 집어넣고, id까지 멤버에다 set id까지 다 해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);//조회
        return Optional.ofNullable(member);//값이 없을수도 있기 때문에 optional로 반환을 해줌
    }

    @Override//findByName같은 것이나 여러개 리스트를 가지고 돌리는 경우에는 특별한 jpql라는 객체지향 쿼리 언어를 사용해야 함. query sql과 같음
    public Optional<Member> findByName(String name) { //조회는 Member.class로 조회할것임
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        
        return em.createQuery("select m from Member m", Member.class)
                .getResultList(); //inline
        //객체를 대상으로 쿼리를 날림. 기존의 sql은 select * 이렇게 하는데, 여기는 객체를 날림
    }
}
