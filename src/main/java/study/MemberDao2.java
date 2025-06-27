package study;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberDao2 {

    private final JdbcClient jdbcClient;

    public MemberDao2(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    private static final RowMapper<Member> MEMBER_MAPPER = new RowMapper<>() {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Member(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("email")
            );
        }
    };

    public void insertMember(Member member) {
        jdbcClient.sql("""
                    insert into member (id, name, age, email)
                values (:id, :name, :age, :email)
                """)
                .param("id",    member.id())
                .param("name",  member.name())
                .param("age",   member.age())
                .param("email", member.email())
                .update();
    }


    public Optional<Member> selectMember(Long id) {
        var sql = "select id, name, age, email from member where id = :id";
        return jdbcClient.sql(sql)
                .param("id", id)
                .query(MEMBER_MAPPER)
                .optional();
    }

    public List<Member> selectAll() {
        return jdbcClient.sql("""
                    select id, name, age, email
                    from member
                    order by id desc
                """)
                .query(MEMBER_MAPPER)
                .list();
    }
}
