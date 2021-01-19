package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    //MVC
    @GetMapping("hello-mvc")
    public String hello(@RequestParam("name") String name, Model model){ //외부에서 파라미터를 받을것임
        model.addAttribute("name",name);//파라미터로 넘어온 name을 넘겨줌
        return "hello-template"; //hello.html을 찾아 실행시켜라! 라는 의미.
    }

    //API
     @GetMapping("hello-string")
    @ResponseBody //html에 나오는 body테그x http 통신부에 body부가 있는데 body부에 이 데이터를 직접 넣어주겠다! 는 의미.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;//만약 name이 spring이면 hello spring이라고 띄움. 이 문자를 그냥 띄우는 것.
        //view와 차이점 : view 이런게 없다. 그냥 문자가 그대로 내려간다! 데이터를 그대로 내려줌.
        //view는 view라는 템플릿이 있는 상황에서 그것을 가지고 조작하는 방식.
    }
    //진짜는 지금부터
    //데이터를 내놓으라고 함. 이 때 API를 많이 사용함.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //문자가 아닌 객체를 넘김. 결과값이 { name: "spring!!" } 이렇게 뜸. 그런데 이것은 json 방식임. {"key : value"}로 이루어져있음.
        //XML 방식은 <html></html>임.
        //json은 { key : value } 형식으로 되어있음. 더 간단해짐.
        //요즘은 json방식으로 반환을 함. 그것이 default로 설정되어있음.
    }
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
