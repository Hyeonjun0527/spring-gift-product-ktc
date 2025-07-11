package study;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestMuch {

    @ValueSource(strings = {"a","b","c"})
    @ParameterizedTest
    void test1(String value) {
        System.out.println("test1");
        System.out.println(this);
        System.out.println(value);
    }
}
