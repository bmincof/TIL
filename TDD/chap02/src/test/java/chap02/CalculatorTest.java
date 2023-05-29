package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    // JUnit은 @Test 애노테이션을 붙인 plus() 메서드를 테스트 메서드로 인식한다.
    // 테스트 메서드는 기능을 검증하는 코드를 담고 있는 메서드이다.
    @Test
    void plus() {
        // 계산 기능을 실행하는 코드를 작성
        int result = Calculator.plus(1, 2);
        // 계산 기능을 실행한 결과가 기대한 값인지 검증한다.
        assertEquals(3, result);
        assertEquals(5, Calculator.plus(4, 1));
    }
}
