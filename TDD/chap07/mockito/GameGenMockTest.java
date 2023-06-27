package appendixC.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

// Mockito의 JUnit 5 확장 기능을 사용
@ExtendWith(MockitoExtension.class)
public class GameGenMockTest {
    // @Mock 애노테이션을 붙인 필드에 대해 자동으로 모의 객체를 생성해 준다
    // mock()을 이용하지 않고도 생성할 수 있음
    @Mock
    private GameNumGen autoCreatedGenMock;

    // Mockito.mock() 메서드는 클래스, 인터페이스, 추상 클래스에 대해 모의 객체를 생성할 수 있다.
    @Test
    void mockTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        // BDDMockito.given() 메서드를 이용해
        // 모의 객체의 메서드가 특정 값을 리턴하도록 설정할 수 있다.
        given(genMock.generate(GameLevel.EASY)).willReturn("123");

        String num = genMock.generate(GameLevel.EASY);
        assertEquals("123", num);
    }

    @Test
    void mockThrowTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        // 지정한 값 대신 익셉션을 발생시킬 수도 있다
        given(genMock.generate(null)).willThrow(IllegalArgumentException.class);
        // 위와 같은 동작을 하는 메서드
        // 타입 대신 익셉션 객체를 인자로 받는 willThrow()를 사용해도 됨
        // given(genMock.generate(null)).willThrow(new IllegalArgumentException());

        assertThrows(IllegalArgumentException.class,
                () -> genMock.generate(null));
    }

    // 리턴 타입이 void인 메서드는 willThrow로 익셉션을 발생시킬 수 있다.
    @Test
    void voidMethodWillThrowTest() {
        List<String> mockList = mock(List.class);
        willThrow(UnsupportedOperationException.class)  // 발생할 익셉션을 인자로 받는다
                .given(mockList)                        // 모의 객체를 전달받는다 (모의 객체의 실행이 아님)
                .clear();                               // 익셉션을 발생시킬 메서드를 호출 (실제로 호출하지는 않고 설정하는 것)

        assertThrows(
                UnsupportedOperationException.class,
                () -> mockList.clear()
        );
    }

    @Test
    void anyMatchTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        // 설정한 인자와 일치하지 않으면 리턴 타입의 기본 값을 리턴한다
        // 이 경우에는 null을 리턴한다
        //
        // given(genMock.generate(GameLevel.EASY)).willReturn("123");
        // String num = genMock.generate(GameLevel.NORMAL);

        // ArgumentMatchers 클래스를 사용하면 정확하게 일치하는 값 대신 임의의 값에 일치하도록 설정할 수 있다
        given(genMock.generate(any())).willReturn("456");

        String num = genMock.generate(GameLevel.EASY);
        assertEquals("456", num);

        String num2 = genMock.generate(GameLevel.NORMAL);
        assertEquals("456", num2);
    }

    @Test
    void mixAnyAndEq() {
        List<String> mockList = mock(List.class);

        // 설정할 인자가 2개 이상일 때 주의점
        // 하나의 값은 임의의 값(ArgumentMatcher를 이용), 하나의 값은 정확한 값(상수) 이어야 할 때
        // 아래와 같이 사용하면 원하는대로 실행되지 않는다.

        // Mockito는 한 인자라도 ArgumentMatcher를 사용해서 설정할 경우 모든 인자를 ArgumentMatcher를 이용해서 설정한다
        // given(mockList.set(anyInt(), "123")).willReturn("456");

        // ArgumentMatcher과 정확한 값을 이용해야 할 때는 아래와 같이 eq() 메서드를 사용한다
        given(mockList.set(anyInt(), eq("123"))).willReturn("456");

        String old = mockList.set(5, "123");
        assertEquals("456", old);
    }

    // 모의 객체의 역할 중 하나는 실제로 모의 객체가 호출됐는지 검증하는 것
    @Test
    void init() {
        GameNumGen genMock = mock(GameNumGen.class);
        Game game = new Game(genMock);
        game.init(GameLevel.EASY);

        // then() : 메서드 호출 여부를 검증할 모의 객체를 전달 받는다
        // should() : 모의 객체의 메서드가 불려야 한다는 것을 설정
        // 뒤에 실제로 호출해야 할 메서드를 지정
        // 정확한 값이 아니라 메서드가 불렸는지가 중요하면 GameLevel.EASY 대신 any() 등을 사용
        then(genMock).should().generate(GameLevel.EASY);
        // Mockito.only()를 should()의 인자로 전달하면 정확하게 한 번만 호출되었는지 검증한다.
        then(genMock).should(only()).generate(GameLevel.EASY);
    }

    // 모의 객체를 호출할 때 사용한 인자를 검증해야 할 때
    // 인자가 객체라면 검증하기 어려울 수 있다. (검증할 값이 많아서)
    // ArgumentCaptor를 사용하면 메서드 호출 여부를 검증하는 과정에서
    // 실제 호출할 때 전달한 인자를 보관할 수 있다. (인자캡처)
    @Test
    void init_captor() {
        GameNumGen genMock = mock(GameNumGen.class);
        Game game = new Game(genMock);
        game.init(GameLevel.EASY);

        // genMock의 메서드 인자로 전달된 GameLevel을 저장할 ArgumentCaptor
        ArgumentCaptor<GameLevel> captor = ArgumentCaptor.forClass(GameLevel.class);
        // genMock의 generate가 실행될 때 인자로 전달된 실제 GameLevel의 값을 저장
        then(genMock).should().generate(captor.capture());

        // 메서드가 호출됐을 때 실제로 전달된 인자 값을 가져온다
        GameLevel realLevel = captor.getValue();
        assertEquals(GameLevel.EASY, realLevel);
    }
}
