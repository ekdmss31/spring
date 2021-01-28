package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//2.자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class SpringConfig {

    //23강

    private final MemberRepository memberRepository;

    @Autowired //생성자가 하나인 경우에는 생략 가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //22강
    /*private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    /*19강-21강
    //spring이 datasource를 제공해줌
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) { //spring이 자체적으로 관리함.
        this.dataSource = dataSource;
    }
    */

    @Bean //2.으로 스프링이 처음 뜰 때,@Configuration을 읽고 스프링에 등록하라는 뜻이네? 하고 인식을 해서 이 로직을 호출해서 스프링 빈에 넣어줌.
    public MemberService memberService(){
        return new MemberService(memberRepository);//그러면 MemberService가 스프링 빈에 등록이 됨.
    }

    //23강 --> 자동으로 springDataJpa가 자동으로 등록해줌.
    /*
    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository(); //MemberRepository는 인터페이스이기 때문에 구현체인 MemoryMemberRepository를 사용함.
        //19강
        //return new JdbcMemberRepository(dataSource);
        //21강 jdbc를 조립해줘야 함.
        //return new JdbcTemplateMemberRepository(dataSource);
        //22강 jpa이용
        //return new JpaMemberRepository(em); //엔티티 매니저라는게 필요함

        return new JpaMemberRepository(memberRepository);

    }*/

    //그러면 결국, Spring이 뜰 때, @Configuration에 의해 이 SpringConfig가 호출되어 MemberService와 MemberRepository가 스프링 빈 컨테이너에 들어가게 됨.

}
