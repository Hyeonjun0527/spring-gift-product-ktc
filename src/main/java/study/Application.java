package study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
        var connection = getConnection();
        createMemberTable(connection);

        Member member = new Member(1L, "최현준", 20, "test@email.com");
//        insertMember(connection, member);

//        connection.close();
    }

    public static void createMemberTable(Connection connection) throws Exception{
        var sql = """
                create table member (
                    id bigint,
                    name varchar(100),
                    age int,
                    email varchar(255),
                    primary key (id)
                );
                """;

        var statement = connection.createStatement();

        statement.execute(sql);
        statement.close();
    }

    public static void insertMember(Connection connection, Member member) throws Exception{
        var sql = """
                insert into member (id, name, age, email) values (?, ?, ?, ?);
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, member.id());
        preparedStatement.setString(2, member.name());
        preparedStatement.setInt(3, member.age());
        preparedStatement.setString(4, member.email());
        preparedStatement.execute();
        preparedStatement.close();

    }

    public static Connection getConnection() throws Exception {
        var url = "jdbc:h2:mem:test";
        var user = "sa";
        var password = "";
        return DriverManager.getConnection(url,user,password);
    }

}
