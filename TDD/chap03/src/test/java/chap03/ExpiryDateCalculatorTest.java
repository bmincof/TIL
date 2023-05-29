package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * 매달 비용을 지불해야 사용할 수 있는 유료 서비스를 구현해보기
 *
 * 검사할 규칙
 * 1. 서비스를 사용하려면 매달 1만 원을 선불로 납부, 납부일 기준으로 한 달 뒤가 서비스 만료일
 * 2. 2개월 이상 요금을 납부할 수 있다.
 * 3. 10만 원을 납부하면 서비스를 1년 제공한다.
 *
 */
public class ExpiryDateCalculatorTest {
    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }

    /*
     * 첫 번째 테스트
     *
     * 만 원 납부 시, 한 달 뒤 같은 날을 만료일로 설정
     */
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 4, 1));
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 5))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 6, 5));
    }

    /*
     * 두 번째 테스트
     *
     * 예외 상황 처리
     * 1. 납부일이 2019-01-31이고 납부액이 1만 원이면 만료일은 2019-02-28
     * 2. 납부일이 2019-05-31이고 납부액이 1만 원이면 만료일은 2019-06-30
     * 3. 납부일이 2020-01-31이고 납부액이 1만 원이면 만료일은 2019-02-29
     */
    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 2, 28));
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 6, 30));
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2020, 2, 29));
    }

    /*
     * 세 번째 테스트
     *
     * 예외 상황 처리
     * 첫 납부일과 만료일 일자가 같지 않을 때
     * 1. 첫 납부일이 2019-01-31이고 만료되는 2019-02-28에 1만 원을 납부하면 다음 만료일은 2019-03-31이다.
     * 2. 첫 납부일이 2019-05-31이고 만료되는 2019-06-30에 1만 원을 납부하면 다음 만료일은 2019-07-31이다.
     */
    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
         PayData payData = PayData.builder()
                 .firstBillingDate(LocalDate.of(2019, 1, 31))
                 .billingDate(LocalDate.of(2019, 2, 28))
                 .payAmount(10_000)
                 .build();

         assertExpiryDate(payData, LocalDate.of(2019, 3, 31));

         PayData payData2 = PayData.builder()
                 .firstBillingDate(LocalDate.of(2019, 1, 30))
                 .billingDate(LocalDate.of(2019, 2, 28))
                 .payAmount(10_000)
                 .build();

         assertExpiryDate(payData2, LocalDate.of(2019, 3, 30));

         PayData payData3 = PayData.builder()
                 .firstBillingDate(LocalDate.of(2019, 5, 31))
                 .billingDate(LocalDate.of(2019, 6, 30))
                 .payAmount(10_000)
                 .build();

         assertExpiryDate(payData3, LocalDate.of(2019, 7, 31));
    }

    /*
     * 네 번째 테스트
     *
     * 1. 2만 원을 지불하면 만료일이 두 달 뒤가 된다.
     * 2. 3만 원을 지불하면 만료일이 세 달 뒤가 된다.
     */
    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2019, 5, 1)
        );

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2019, 6, 1)
        );
    }

    /*
     * 다섯 번째 테스트
     *
     * 예외 상황 처리
     * 1. 첫 납부일이 2019-01-31이고 만료되는 2019-02-28에 2만 원을 납부하면 다음 만료일은 2019-04-30이다.
     */
    @Test
    void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 31))
                        .billingDate(LocalDate.of(2019, 2, 28))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2019, 4, 30)
        );

        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 3, 31))
                        .billingDate(LocalDate.of(2019, 4, 30))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2019, 7, 31)
        );
    }

    /*
     * 여섯 번째 테스트
     *
     * 10개월 요금 (10만 원)을 납부하면 1년 제공
     */
    @Test
    void 십만원을_납부하면_1년_제공() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 28))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2020, 1, 28)
        );
    }

    /*
     * 일곱 번째 테스트
     *
     * 예외 상황 처리
     * 1. 2020-02-29에 10만 원을 납부하면 다음 만료일은 2021-02-28이다.
     */
    @Test
    void 윤달_마지막날_십만원을_납부() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020, 2, 29))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2021, 2, 28)
        );
    }

    /*
     * 여덟 번째 테스트
     *
     * 10만 원 이상을 납부하면 10만원 단위는 1년으로 계산하기
     * 13만 원을 납부하면 1년 3개월
     */
    @Test
    void 십만원_이상_납부하면_십만원_단위는_일년으로_계산() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 28))
                        .payAmount(130_000)
                        .build(),
                LocalDate.of(2020, 4, 28)
        );
    }
}
