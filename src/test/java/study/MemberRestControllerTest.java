package study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberRestControllerTest {

    @LocalServerPort
    private int port;

    private RestClient client = RestClient.builder().build();

    //얘는 클래스 실행될 때 한 번
    @BeforeAll
    static void beforeAll() {

    }

    @BeforeEach
    void setUp() {
        System.out.println("setUp");
    }

    @Test
    void 존재하지_않는_아이디로_조회하면_404가_반환된다() {
        String url = "http://localhost:" + port + "/api/members/2";
        RestClient.ResponseSpec retrieve = client.get()
                .uri(url)
                .retrieve();
        //200인지 확인
//        assertThat(retrieve.toBodilessEntity()
//                .getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

        //toEntity(Member.class)했는데 toBodilessEntity로 바꿨음

        Assertions.assertThatExceptionOfType(HttpClientErrorException.NotFound.class)
                .isThrownBy(
                        ()->
                        client.get()
                                .uri(url)
                                .retrieve()
                                .toBodilessEntity()
                );

//        var actual = retrieve.toEntity(Member.class).getBody();
//        assertThat(Objects.requireNonNull(actual).name()).isEqualTo("choi");
    }

    @Test
    void test2() {

    }
}
