package chap05;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * 테스트 클래스의 생명주기 알아보기
 *
 * 코드 실행 결과
 * new Lifecycle Test
 * setUp
 * A
 * tearDown
 * new Lifecycle Test
 * setUp
 * B
 * tearDown
 */
public class LifecycleTest {
    public LifecycleTest() {
        System.out.println("new Lifecycle Test");
    }

    @BeforeEach
    void setUp() {
        System.out.println("setUp");
    }

    @Test
    void a() {
        System.out.println("A");
    }

    @Test
    void b() {
        System.out.println("B");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown");
    }

}
