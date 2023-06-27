package chap07.autodebit;

import chap07.user.RegisterResult;
import chap07.user.StubAutoDebitInfoRepository;
import chap07.user.StubCardNumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chap07.autodebit.CardValidity.INVALID;
import static chap07.autodebit.CardValidity.THEFT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoDebitRegister_Stub_Test {
    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
    private StubAutoDebitInfoRepository stubRepository;

    // 대역을 이요한 테스트 코드 작성
    // Stub 객체를 사용해서 카드번호가 유효한지 검사하게 된다.
    @BeforeEach
    void setUp() {
        stubValidator = new StubCardNumberValidator();
        stubRepository = new StubAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, stubRepository);
    }

    @Test
    void invalidCard() {
        stubValidator.setInvalidNo("111122223333");

        AutoDebitReq req = new AutoDebitReq("user1", "111122223333");
        RegisterResult result = register.register(req);

        assertEquals(INVALID, result.getValidity());
    }

    @Test
    void theftCard() {
        stubValidator.setInvalidNo("1234567890123456");

        AutoDebitReq req = new AutoDebitReq("user1", "1234567890123456");
        RegisterResult result = register.register(req);

        assertEquals(THEFT, result.getValidity());
    }
}
