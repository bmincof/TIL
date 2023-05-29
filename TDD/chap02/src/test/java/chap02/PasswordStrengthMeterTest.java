package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * 암호 검사기 - 문자열을 검사해서 규칙을 준수하는지에 따라 암호를 약함, 보통, 강함으로 구분
 *
 * 검사할 규칙
 * 1. 길이가 8글자 이상
 * 2. 0~9 사이의 숫자를 포함
 * 3. 대문자 포함
 *
 * -->  강함: 규칙을 모두 충족
 *      보통: 2개의 규칙을 충족
 *      약함: 1개 이하의 규칙을 충족
 */
public class PasswordStrengthMeterTest {
    // 모든 테스트에서 반복적으로 수행해야 하므로 중복을 제거했다.
    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    // password 파라미터의 강도를 구하고 그 값이 expStr 파라미터와 같은지 비교하는 메서드
    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }

    /* 첫 번째 테스트를 잘 선택하는 것이 중요하다
     * 그렇지 않으면 이후 진행 과정이 순탄하게 흘러가지 않는다.
     * 가장 쉽거나 가장 예외적인 상황을 선택하자
     *
     * ex) 모든 조건을 충족하는 경우, 모든 조건을 충족하지 않는 경우
     * -> 모든 조건을 충족하지 않는 경우는 각 조건을 검사하는 코드를 모두 만들어야 하므로
     * 구현을 다 한 후에 테스트하는 방식과 크게 다르지 않다.
     *
     * 모든 조건을 충족하는 경우부터 진행해보자
     */
    @Test
    void meetsAllCriteria_Then_Strong() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!@Add", PasswordStrength.STRONG);
    }

    /*
     * 두 번째 테스트
     *
     * 길이만 8글자 미만이고 나머지 조건은 충족하는 경우
     */
    @Test
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    /*
     * 세 번째 테스트
     *
     * 숫자를 포함하지 않고 나머지 조건은 충족하는 경우
     */
    @Test
    void meetsOtherCriteria_except_for_number_Then_Normal() {
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    /*
     * 네 번째 테스트
     *
     * 값이 없는 경우
     */
    @Test
    void nullInput_Then_Invalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    void emptyInput_Then_Invalid() {
        assertStrength("", PasswordStrength.INVALID);
    }

    /*
     * 다섯 번째 테스트
     *
     * 대문자를 포함하지 않고 나머지 조건을 충족하는 경우
     */
    @Test
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    /*
     * 여섯 번째 테스트
     *
     * 길이가 8글자 이상인 조건만 충족하는 경우
     */
    @Test
    void meetsOnlyLengthCriteria_Then_Weak() {
        assertStrength("abdefghi", PasswordStrength.WEAK);
    }

    /*
     * 일곱 번째 테스트
     *
     * 숫자 포함 조건만 충족하는 경우
     */
    @Test
    void meetsOnlyNumCriteria() {
        assertStrength("12345", PasswordStrength.WEAK);
    }

    /*
     * 여덟 번째 테스트
     *
     * 대문자 포함 조건만 충족하는 경우
     */
    @Test
    void meetsOnlyUpperCriteria() {
        assertStrength("ABZEF", PasswordStrength.WEAK);
    }

    /*
     * 아홉 번째 테스트
     *
     * 아무 조건도 충족하지 않은 경우
     */
    @Test
    void meetsNoCriteria_Then_Weak() {
        assertStrength("abc", PasswordStrength.WEAK);
    }
}
