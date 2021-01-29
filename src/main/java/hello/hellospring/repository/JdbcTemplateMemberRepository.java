package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*인젝션 : 쿼리를 보내는 기법.*/
public class JdbcTemplateMemberRepository implements MemberRepository{

    //Jdbc Template을 쓸 때 맨 처음으로 이것을 써줘야 함
    private final JdbcTemplate jdbcTemplate;

    //얘는 인젝션을 받을 수 있는 것이 아님. 그래서 datasoruce 인젝션을 받고, 이 형태로 변환하는 것이 spring이 권장함
    /*
    @Autowired //생성자가 딱 하나이면 spring bean으로 등록되면 Autowired 생략 가능
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    */
    public JdbcTemplateMemberRepository(DataSource dataSource) {//spring이 dataSource를 자동으로 인젝션 해줌
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        //document에서 자세하게 나옴.

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        //위 두줄을 이용하면 쿼리를 짤 필요가 없음.

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { // 조회
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
        //긴 코드를 이렇게 두줄로 변경 가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }
    
    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> { //람다 스타일로 바꿈
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
