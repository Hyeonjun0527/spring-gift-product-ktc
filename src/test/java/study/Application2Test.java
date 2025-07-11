package study;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
public class Application2Test {
    @Autowired
    private ApplicationContext context;

    @MockitoBean
    private MemberService memberService;

    @Test
    void test1() {
        System.out.println("app2 : test1");
        System.out.println(this);
        System.out.println(context);
        System.out.println(System.identityHashCode(memberService));
    }

    @Test
    void test2() {
        System.out.println("app2 : test1");
        System.out.println(this);
        System.out.println(context);
        System.out.println(System.identityHashCode(memberService));
    }
}
