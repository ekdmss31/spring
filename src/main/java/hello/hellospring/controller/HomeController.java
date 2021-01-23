package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") //처음 localhost:8080에 들어가면 /이 호출이 됨.
    public String home() {

        return "home";//그 다음 이 home.html이 호출이 될 것임.

    }

}
