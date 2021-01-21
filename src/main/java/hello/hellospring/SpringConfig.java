package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//2.자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class SpringConfig {

    @Bean //2.으로 스프링이 처음 뜰 때,@Configuration을 읽고 스프링에 등록하라는 뜻이네? 하고 인식을 해서 이 로직을 호출해서 스프링 빈에 넣어줌.
    public MemberService memberService(){
        return new MemberService(memberRepository());//그러면 MemberService가 스프링 빈에 등록이 됨.
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository(); //MemberRepository는 인터페이스이기 때문에 구현체인 MemoryMemberRepository를 사용함.
    }

    //그러면 결국, Spring이 뜰 때, @Configuration에 의해 이 SpringConfig가 호출되어 MemberService와 MemberRepository가 스프링 빈 컨테이너에 들어가게 됨.

}
