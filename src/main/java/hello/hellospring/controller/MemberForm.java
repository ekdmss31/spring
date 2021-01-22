package hello.hellospring.controller;

public class MemberForm { //이것을 만들었으면 MemberController에도 추가해줘야함
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
