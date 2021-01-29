package hello.hellospring.domain;

import javax.persistence.*;

//22.
@Entity //jpa가 관리하는 entity임을 의미함.
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //sequence를 맵핑시킴. db에서 자동으로 생성하는 시퀀스.
    private Long id;//시스템이 저장하는 id

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
         this.name = name;
    }
}
