package study;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
public class ApplicationTest {
    @Autowired
    private ApplicationContext context;

    @DirtiesContext
    @Test
    void test1() {
        System.out.println("test1");
        System.out.println(this);
        System.out.println(context);
    }

    @Test
    void test2() {
        System.out.println("test2");
        System.out.println(this);
        System.out.println(context);
    }
}
