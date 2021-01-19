package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
     MemoryMemberRepository repository = new MemoryMemberRepository();
    
    @AfterEach //이 AfterEach가 callBack method라고 보면 됨. 메서드 하나 끝날때마다 실행이 됨. 어떤 동작을 함.
    public void afterEach(){//test가 하나 끝날때마다 메모리를 clear해줘야 함.
        repository.clearStore();//MemoryMemberRepository에서 만들어준 메서드 이용.
        //test가 하나씩 실행될 때 마다 한번씩 저장소를 지움.
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();//findById(member.getId())는 optional값임. optional에서 값을 꺼낼 때는 get()을 이용함.

        //검증해보기. 같으면 참.
        System.out.println("result = " + (result == member));

        //글자로 계속 볼수는 없기 때문에 assert이용
        //Assertions.assertEquals(member,result); //(기대했던값, 실제값)
        //같으면 초록불이 뜨고, 다르면 빨간불이 뜸.

        //요즘은 이 방법을 많이 사용함
        //Assertions.assertThat(member).isEqualTo(result);를 alt+enter하면 아래와 같이 변함
        assertThat(member).isEqualTo(result);
        //값이 다르면 빨간불, 같으면 초록불이 뜨게 함.
    }

    //이름으로 찾기도 test 해봐야 함
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); //이름이 같으면 shift+f6 누르면 rename가능
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    //findAll
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}

