package chap07.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// 회원 가입에 대한 핵심 로직을 수행
// 암호의 강도는 UserRegister가 아닌 WeakPasswordChecker를 통해 검사
// 회원 가입에 성공하면 이메일로 회원 가입 안내 메일을 발송
public class UserRegisterTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker stubPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubPasswordChecker, fakeRepository, spyEmailNotifier);
    }

    @DisplayName("약한 암호면 가입 실패")
    @Test
    void weakPassword() {
        // 암호가 약하다고 응답하도록 설정
        stubPasswordChecker.setWeak(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    @Test
    void dupIdExists() {
        // 이미 같은 ID가 존재하는 상황으로 설정
        fakeRepository.save(new User("id", "pw1", "email@email.com"));

        assertThrows(DupIdException.class, () -> {
            userRegister.register("id", "pw2", "email");
        });
    }

    @DisplayName("같은 ID가 없으면 가입 성공함")
    @Test
    void noDupId_RegisterSuccess() {
        userRegister.register("id", "pw", "email");

        // 가입 결과 확인
        User savedUser = fakeRepository.findById("id");
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

    @DisplayName("가입하면 메일을 전송함")
    @Test
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");

        assertTrue(spyEmailNotifier.isCalled());
        // 메일을 전송한 주소가 입력한 주소와 일치하는지 확인
        assertEquals("email@email.com", spyEmailNotifier.getEmail());
    }
}
