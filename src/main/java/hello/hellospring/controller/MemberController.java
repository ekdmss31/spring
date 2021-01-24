package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


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


    @GetMapping("/members/new") //기본적으로 url창에 주소를 치는것.
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")//데이터를 form에 넣어서 전달할 때 post 이용함. url이 동일하지만, post로 넘겨주었기 때문에 post가 선택이 됨.
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());//멤버가 만들어짐

        memberService.join(member);//회원가입을 함.
        //join > save > Member save에 stroe.put이 됨

        return "redirect:/"; //회원가입이 끝난 후 홈 화면으로 보냄.
    }

    @GetMapping("/members")//회원목록은 members로 가게 해놨음
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);//멤버의 리스트를 모델에 담아서 화면에 넘길것임
        return "members/memberList";
    }
}
