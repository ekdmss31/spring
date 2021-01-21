package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller//1.2. 2.를 할 때에도 Controller는 그대로 둠.
public class MemberController {//1.spring container라는 통이 생기는데, 거기에 이 멤버 컨트롤러 객체를 생성해서 스프링에 넣어둠. 그래서 컨트롤러에서 관리를 함.
    //1.
    //그래서 얘도 스프링 컨트롤러가 관리하게 됨.
    //private final MemberService memberService = new MemberService();
    //이렇게 쓸 수도 있는데, 스프링이 관리를 하게 되면 다 스프링 컨테이너에 등록을 하고, 스프링 컨테이너에서 받아서 쓰도록 바꿔야 함.
    //왜냐하면 이렇게 new해서 하면 멤버 컨트롤러 말고 다른 여러 컨트롤러들이 멤버 서비스를 가져다 쓸 수 있게 됨.
    //ex)주문컨트롤러에서도 멤버서비스를 가져다 쓸수있고... 왜냐하면 회원은 여러군데에서 쓰이기 때문에.
    //이 객체를 new하면 별 기능이 없음. 하나하나 생성할 필요 없음. 따라서 하나를 생성해서 공용으로 쓰면 됨.
    //그래서 아래와 같이 바꿔줌 --> 스프링 컨테이너에 하나만 등록하고, 그것을 공용해서 쓰는 방법.
    //생성자로 사용하는 것. @Autowired 이용.

    private final MemberService memberService;

    @Autowired //2.SpringConfig의 MemberService를 이용하여 연결시킴.
    public MemberController(MemberService memberService) {//1.@Autowired를 사용하면 스프링이 컨테이너에 있는 멤버서비스를 가져와서 여기다가 연결시켜줌
        this.memberService = memberService;
    }//1.하지만 오류가 뜸. 왜냐하면 MemberService class에 가보면 얘는 그냥 순수한 java 코드임.. 스프링이 얘를 알 수 있는 방법이 없음. 따라서 MemberService에 @Service라고 넣어줘야함.
}
