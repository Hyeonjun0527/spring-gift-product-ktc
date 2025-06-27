package study;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class MemberDao {
    private final JdbcTemplate jdbcTemplate;

    public MemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertMember(Member member) {
        var sql = "insert into member(id, name, age, email) values(?, ?, ?, ?);";
        jdbcTemplate.update(sql, member.id(), member.name(), member.age(), member.email());

    }

}
