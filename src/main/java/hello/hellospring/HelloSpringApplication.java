package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}
	//build tool은 의존관계를 가진다. 우리는 하나만 필요하지만, 그 필요한 것이 다른것을 필요로 한다면 같이 가져오게 된다.
	//그것이 바로 gradle에 있는 Dependencies이다.
	//tomcat은 ㅇ{ㅅ날에 웹서버를 직접 서버에 설치해 놓아서 그곳에 자바 코드를 밀어넣는 식으로 해서 웹서버와 개발라이브러리가 분리되어 있었다.
	//그런데 요즘에는 소스라이브러리에서 내장되어 있다. 설정이 필요가 없다. 그래서 바로 8080으로 들어갈 수 있는 것이다.
	//스프링 부트를 설치하면 코어도 땡겨와서 다 사용을 한다.

	//starter에 들어가보면 logging이 있는데, slf가 있고 logback을 둘 다 사용을 한다.

	//test와 관련된 라이브러리가 있는데, junit이라는 라이브러리를 test할때 많이 쓴다.
}
