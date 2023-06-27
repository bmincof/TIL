package chap07.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRegisterMockTest {
    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker,
                fakeRepository,
                mockEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        BDDMockito
                // "pw" 인자를 사용해서 모의 객체의 checkPasswordWeak 메서드를 호출하면
                .given(mockPasswordChecker.checkPasswordWeak("pw"))
                // 결과로 true를 리턴하도록 설정
                .willReturn(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @DisplayName("회원 가입시 암호 검사 수행함")
    @Test
    void checkPassword() {
        userRegister.register("id", "pw", "email");

        BDDMockito
                // 인자로 전달한 mockPasswordChecker 모의 객체의
                .then(mockPasswordChecker)
                // 특정 메서드가 호출됐는지 검증하는데
                .should()
                // 임의의 String 타입 인자를 이용해서 checkPasswordWeak() 메서드 호출 여부를 확인한다.
                .checkPasswordWeak(BDDMockito.anyString());
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        BDDMockito
                .then(mockEmailNotifier)
                .should()
                // mockEmailNotifier의 sendRegisterEmail()이 호출될 때
                // 전달받은 인자를 captor에 저장한다
                .sendRegisterEmail(captor.capture());

        String realEmail = captor.getValue();
        assertEquals("email@email.com", realEmail);
    }
}
