package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//1.컴포넌트 스캔으로 스프링 빈 등록하기
//@Service //이 코드를 넣어줘야 스프링이 알아챔. Service를 사용하면, 스프링이 올라올 때, 어? Service네? 하고 spring이 컨테이너에 멤버서비스를 등록해줌.
public class MemberService {
    //private final MemberRepository memberRepository = new MemoryMemberRepository();//** 이것을 변경해줄것임. MemberServiceTest에서 같은 객체를 이용하기 위해서.
    private final MemberRepository memberRepository ;

    //1.@Autowired //MemberController와 연결시켜주기 위해 필요함.
    public MemberService(MemberRepository memberRepository) {//MemberRepository는 자동으로 컨테이너에 넣어줌. 왜냐하면 MemberService가 컨테이너에 들어있기 때문에.
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){ // 회원 가입
        //회원가입할 때 같은이름이 있으면 안된다. 라고 비즈니스 로직을 짬.
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {//값이 있으면
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */
        //위의 코드를 아래로 변경.
        validateDuplicateMember(member); //중복회원 검증

        memberRepository.save(member);//save만 호출해주면 됨

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {//중복회원 검증
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {//값이 있으면
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
