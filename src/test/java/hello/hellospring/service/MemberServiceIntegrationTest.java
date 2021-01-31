package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
// 이것을 test에 넣으면 test를 실행할때, 트랜잭션을 먼저 실행하고, db에 다 넣은다음에, test가 끝나면 rollback을 해줌. 그래서 db에 넣었던 데이터가 깔끔하게 반영이 안되고 다 지워짐.
@Transactional // db에 실제 데이터가 반영이 안됨. 그래서 저번에 했던것처럼 지우는 코드를 따로 안넣어도 됨.
public class MemberServiceIntegrationTest {//spring을 이용해서 test만들기

    @Autowired MemberService memberService ;
    @Autowired MemberRepository memberRepository;

    @Test
    //@Commit // 이 commit을 해주면 값이 db에 들어감
    void 회원가입() {// test는 한글로 바꿔도 됨.
        // given when then 문법.무엇인가 주어졌는데, 이것을 실행했을 때 이게 나와야 해. 라는 의미.
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member); // return이 저장한 id가 나오기로 했음.

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        // 이것때문에 try catch넣기 애매하니까 이 문법을 이용함.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 이 예외(MemberService의 validateDuplicateMember)가 터져야 하고,

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }

}