# **대역의 필요성**

테스트 코드를 작성하다보면 파일 시스템, DB, HTTP 통신 등 외부 요인이 필요한 시점이 있다. 테스트 대상이 이런 외부 요인에 의존하면 테스트를 작성하고 실행하기 어려워진다.

나의 경우 API 요청이 필요한 테스트를 작성한 적이 있었다. 테스트를 작성하고 실행할 때마다 API 요청을 보내니까 API 요청 제한 횟수로 인해 어느시점부터 테스트가 실패하게 됐다.

# **대역을 이용한 테스트**

외부 요인에 영향을 받는 클래스를 흉내내는 대역 클래스를 만들어 테스트에 이용하자. 이런 대역 클래스는 실제로 기능을 구현할 필요는 없고 단순한 구현으로 실제 상황을 재현해주기만 하면 된다. (예제코드를 확인하자)

대역을 사용하여 외부 요인을 흉내내면 API 연동이나 실제 DB 등을 사용하지 않고 원하는 테스트를 수행할 수 있다.

# **대역의 종류**

<table>
  <thead>
    <tr>
      <th>대역 종류</th>
      <th>설명</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>스텁 (Stub)</td>
      <td>구현을 단순한 것으로 대체한다. 테스트에 맞게 단순히 원하는 동작을 수행한다.</td>
    </tr>
    <tr>
      <td>가짜 (Fake)</td>
      <td>실제 제품에는 적합하지 않지만, 실제로 동작하는 구현을 제공한다.</td>
    </tr>
    <tr>
      <td>스파이 (Spy)</td>
      <td>호출된 내역을 기록한다. 기록한 내용은 테스트 결과를 검증할 때 사용한다. Stub에 포함된다.</td>
    </tr>
    <tr>
      <td>모의 (Mock)</td>
      <td>기대한 대로 상호작용하는지 행위를 검증한다.</td>
    </tr>
  </tbody>
</table>

# **Mockito 프레임워크 기초**

자바 기반의 모의 객체 생성, 검증, 스텁을 지원하는 프레임워크

대역을 쉽게 사용할 수 있도록 해준다.

## 기능

- 모의 객체 생성

  mock(SomeClass.class) 의 형식으로 특정 타입의 모의 객체를 생성할 수 있다

  mockito-junit-jupiter 의존을 추가하고 테스트 클래스에 `@ExtendWith(MockitoExtension.class)`를 사용하면 변수 선언부에 `@Mock`을 이용해 모의 객체를 생성할 수도 있다

- 스텁 설정

  BDDMockito.given()을 통해 모의 객체의 메서드가 특정한 값을 리턴하도록 할 수 있다

  ```Java
  SomeClass someClass = mock(SomeClass.class);

  // @Mock으로 모의 객체 생성
  @Mock
  OtherClass otherclass;

  // someMethod()가 123을 받아 실행되면 "456"을 리턴
  given(someClass.someMethod(123)).willReturn("456");

  // someMethod()가 456을 받아 실행되면 익셉션 발생
  given(someClass.someMethod(456)).willThrow(Exception.class);
  ```

- 인자 매칭 처리

  ArgumentMatchers 클래스를 사용하면 정확하게 일치하는 값 대신 임의의 값으로 일치하도록 설정할 수 있다.

  ```Java
  SomeClass someClass = mock(SomeClass.class);

  // 모든 값에 대해 "456"을 리턴
  given(someClass.someMethod(any())).willReturn("456");

  // 의도한대로 동작하지 않을 수 있음
  /* 인자가 여러 개 필요한 메서드의 경우
  하나라도 ArgumentMatcher를 사용하면
  나머지도 ArgumentMatcher로 처리됨
  */
  given(someClass.someMethod(anyInt(), "123")).willReturn("456");
  ```

  ### ArgumentMatchers 클래스의 메서드

  <table>
    <tbody>
      <tr>
        <td>anyInt(), anyDouble(), anyBoolean(), ...</td>
        <td>기본 데이터 타입에 대한 임의 값 일치</td>
      </tr>
      <tr>
        <td>anyString()</td>
        <td>문자열에 대한 임의 값 일치</td>
      </tr>
      <tr>
        <td>any()</td>
        <td>임의 타입에 대한 일치</td>
      </tr>
      <tr>
        <td>anyList(), anySet(), anyMap(), anyCollection()</td>
        <td>임의 컬렉션에 대한 일치</td>
      </tr>
      <tr>
        <td>matches(String), matches(Pattern)</td>
        <td>정규표현식을 이용한 String 값 일치 여부</td>
      </tr>
      <tr>
        <td>eq()</td>
        <td>특정 값과 일치 여부</td>
      </tr>
    </tbody>
  </table>

- 행위 검증

  실제로 모의 객체가 호출됐는지 검증할 수 있다.

  ```Java
  // should() 에 체이닝 된 메서드 (someMethod)가 호출되었는지 검증
  then(someClass).should().someMehod(any());

  // (someMethod)가 한 번만 호출되었는지 검증
  then(someClass).should(only()).someMehod(any());
  ```

  ### 메서드 호출 횟수를 검증하기 위한 메서드

  <table>
    <tbody>
      <tr>
        <td>only()</td>
        <td>한 번만 호출</td>
      </tr>
      <tr>
        <td>times(int)</td>
        <td>지정한 횟수만큼 호출</td>
      </tr>
      <tr>
        <td>never()</td>
        <td>호출하지 않음</td>
      </tr>
      <tr>
        <td>atLeast(int)</td>
        <td>적어도 지정한 횟수만큼 호출</td>
      </tr>
      <tr>
        <td>atLeastOnce()</td>
        <td>atLeast(1)과 동일</td>
      </tr>
      <tr>
        <td>atMost(int)</td>
        <td>최대 지정한 횟수만큼 호출</td>
      </tr>
    </tbody>
  </table>

- 인자 캡쳐

  ArgumentCaptor를 이용하면 모의 객체를 호출할 때 실제로 사용된 인자를 저장할 수도 있다.

  ```Java
  ArgumentCaptor<Integer> captor =
                  ArgumentCaptor.forClass(Integer.class);

  // someMethod가 실행될 때 전달받는 인자를 captor에 저장
  then(someClass).should().someMehod(captor.capture());

  // 실제 전달됐던 값을 사용할 수 있다
  Integer realValue = captor.getValue();
  ```

# **상황과 결과 확인을 위한 의존 도출과 대역 사용**

외부 API호출과 같이 제어하기 힘든 외부 상황이 존재한다면 다음과 같은 방법으로 의존을 도출하고 대역으로 대신할 수 있다.

- 제어하기 힘든 외부 상황을 별도 타입으로 분리
- 테스트 코드는 별도로 분리한 타입의 대역을 생성
- 생성한 대역을 테스트 대상의 생성자 등을 이용해서 전달
- 대역을 이용해서 상황 구성

=> 테스트하기 힘든 기능은 별도의 클래스로 분리해 대역 객체로 만들어 테스트 진행

# **대역과 개발 속도**

실제 구현 대신 대역을 사용해 테스트를 진행한다면 대기 시간이 줄어들어 개발 속도가 증가한다.

예를 들어 외부 API에 의존하는 테스트를 작성할 경우, 외부 API에 문제가 생겼다면 이를 해결해주기 전까지 대기해야한다.

DB를 아직 실제로 구현하지 않았더라도 실행 결과를 확인할 수 있다.

다른 개발자가 구현중인 기능을 이용해야 할 때 대역을 이용하면 바로 실행 결과를 확인할 수 있다.

즉, 의존 대상이 아직 구현되지 않았어도 테스트 대상을 완성할 수 있도록 만들어주므로 개발 속도를 올리는데 도움이 된다.

# **모의 객체를 과하게 사용하지 않기**

모의 객체를 사용하는 것은 장점만 있는 것은 아니다. 과하게 사용할 경우 테스트 코드가 오히려 복잡해지는 경우도 발생한다.

경우에 따라서는 번거롭더라도 가짜 구현을 사용해 테스트를 작성하는 것이 의미나 구조를 더 간단하게 하는데 도움이 된다. 대표적으로 DAO나 Repository 같은 저장소에 대한 대역이 그렇다.
