package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //controller는 이것을 적어줘야 함.
public class HelloController {

    //Mep application에서 hello라고 들어오면 이 메서드를 호출해줌,
    @GetMapping("hello")
    public String hello(Model model){
        //model이란? MVC라고 하는데..
            //아래에서 key값이 data가 되고, value가 hello가 됨. hello.html의 ${data}가 이 value로 치환이 되게 됨
        model.addAttribute("data","hello!!!");
        return "hello"; //hello.html을 찾아 실행시켜라! 라는 의미.
    }
}
