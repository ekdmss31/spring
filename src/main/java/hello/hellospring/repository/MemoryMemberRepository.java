package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

//MemberRepository의 구현체 생성.
public class MemoryMemberRepository implements MemberRepository{

    //메모리 저장을 위해 map 이용
    private static Map<Long, Member> store = new HashMap<>();//key : Long, value : Member
    private static long sequence = 0L;//0 1 2 ... 숫자

    @Override
    public Member save(Member member) {
        member.setId(++sequence);//id는 시스템이 정해주는 것. 따라서 sequence를 증가시켜 set해줌
        store.put(member.getId(), member);//map에 저장함.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.of(store.get(id));//null이 반환될 가능성이 있기 때문에 optional이용.  이렇게 반환해주면 client에서 뭘 할수있음.
    }

    @Override
    public Optional<Member> findByName(String name) {
        //루프를 돌려서 getName이 파라미터로 넘어온 name과 같은지 확인함. 
        //같은 경우에는 필터링되고, 그 중에서 찾으면 반환을 함,
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        //findAny()는 하나라도 찾는 것인데, 결과가 optional로 반환이 됨.
        //끝까지 찾았는데 없으면 optional에 null이 포함되어 반환됨
    }

    @Override
    public List<Member> findAll() {//사용하는 것은 Map인데, 여기는 List로 반환이 됨. (실무에서 자바는 list를 많이 이용함)
        return new ArrayList<>(store.values());//Map의 value값인 Member를 반환함.
    }

    //구현이 끝났는데, 검증을 해야함. 제대로 동작을 하는지. 이 때 사용하는 것이 test case를 작성하는 것임.

    public void clearStore(){  //store를 싹 비움.
        store.clear();
    }
}
