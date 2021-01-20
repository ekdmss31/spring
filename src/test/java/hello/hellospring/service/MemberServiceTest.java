package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceTest {

    //MemberService memberService = new MemberService();

    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();//clear를 사용하기 위해 필요함.
    //MemberService에서의 MemoryMemberRepository와 다른 객체가 생성되었음. 따라서 MemberService에서 같은 객체를 쓸 수 있게 해줄것임.
    //다른 인스턴스를 사용하는 것이기 때문에 만약 MemberService에서 static이 아니었으면 다른 db를 쓰는 것과 마찬가지인 셈.
    //같은 인스턴스를 사용하기 위해서 MemberService에서 *과 같이 변경해준것임.
    //여기서는 아래와 같이 변경해줌
    MemoryMemberRepository memberRepository;
    MemberService memberService ;
    @BeforeEach
    public void beforeEach(){//각 test를 하기 전에 memberRepository를 만들고 memberService에 넣으면 같은 메모리 리퍼지토리가 사용이 되는 것임.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        //memberService입장에서 보면 외부에서 memberRepository를 넣어줌. 이것을 디펜던시 인섹션(DI)라고 함.
    }
    
    @AfterEach //이 AfterEach가 callBack method라고 보면 됨. 메서드 하나 끝날때마다 실행이 됨. 어떤 동작을 함.
    public void afterEach(){//test가 하나 끝날때마다 메모리를 clear해줘야 함.
        memberRepository.clearStore();//MemoryMemberRepository에서 만들어준 메서드 이용.
        //test가 하나씩 실행될 때 마다 한번씩 저장소를 지움.
    } //돌때마다 메모리가 clear가 됨.

    @Test
    void 회원가입() {//test는 한글로 바꿔도 됨.
        //given when then 문법.무엇인가 주어졌는데, 이것을 실행했을 때 이게 나와야 해. 라는 의미.
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member); //return이 저장한 id가 나오기로 했음.

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

        //memberService.join(member2); //여기서 이 예외가 터지게 됨.
 /*       try {
            memberService.join(member2);
            fail("예외처리 되었습니다");
        } catch (IllegalStateException e){ //예외가 아니면 성공적인것
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        //이것때문에 try catch넣기 애매하니까 이 문법을 이용함.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//이 예외(MemberService의 validateDuplicateMember)가 터져야 하고,

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }


    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}