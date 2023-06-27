package chap07.autodebit;

import chap07.user.JpaAutoDebitInfoRepository;
import chap07.user.RegisterResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chap07.autodebit.CardValidity.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegisterTest {
    private AutoDebitRegister register;

// 아래의 테스트들을 진행하려면 외부 서비스에서 테스트 목적의 데이터나 개발 환경을 제공해야한다.
// 제공해주더라도 기간이 만료되거나 내용이 변경되면 테스트는 실패하게 된다.
// 이럴 때 외부 서비스를 대신할 대역을 사용할 수 있다.

    @BeforeEach
    void setUp() {
        CardNumberValidator validator = new CardNumberValidator();
        AutoDebitInfoRepository repository = new JpaAutoDebitInfoRepository();
        register = new AutoDebitRegister(validator, repository);
    }

    @Test
    void validCard() {
        // 업체에서 받은 테스트용 유효한 카드번호 사용
        AutoDebitReq req = new AutoDebitReq("user1,", "1234123412341234");
        RegisterResult result = this.register.register(req);
        assertEquals(VALID, result.getValidity());
    }

    @Test
    void theftCard() {
        // 업체에서 받은 도난 테스트용 카드번호 사용
        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
        RegisterResult result = this.register.register(req);
        assertEquals(THEFT, result.getValidity());
    }
}
