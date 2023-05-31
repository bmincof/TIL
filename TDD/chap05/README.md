# **JUnit 5 기초**

@Test와 assertEquals() 외에 JUnit 5의 기초적인 기능을 추가적으로 알아보기 (JUnit 5.5 버전 기준)

# **JUnit 5 모듈**

- JUnit 플랫폼

  테스팅 프레임워크를 구동하기 위한 런처와 테스트 엔진을 위한 API를 제공

- JUnit 주피터(Jupiter)

  JUnit 5를 위한 테스트 API와 실행 엔진을 제공

- JUnit 빈티지(Vintage)

  JUnit 3과 4로 작성된 테스트를 JUnit 5 플랫폼에서 실행하기 위한 모듈을 제공

# **@Test 애노테이션과 테스트 메서드**

테스트 메서드에 @Test 애노테이션을 메서드에 붙이면 된다. 단, @Test 애노테이션을 붙인 메서드는 private이면 안 된다.

# **주요 단언 메서드**

JUnit의 Assertion 클래스는 assertEquals()를 포함한 다양한 정적 메서드를 제공한다.

<table>
  <thead>
    <tr>
      <th>메서드</th>
      <th>설명</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>
        assertEqauls(expected, actual) 
      </td>
      <td> 
        실제 값이 기대하는 값과 같은지 검사한다
      </td>
    </tr>
    <tr>
      <td>
        assertNotEqauls(expected, actual) 
      </td>
      <td> 
        실제 값이 기대하는 값과 같지 않은지 검사한다
      </td>
    </tr>
    <tr>
      <td>
        assertSame(Object expected, Object actual) 
      </td>
      <td> 
        두 객체가 동일한 객체인지 검사한다
      </td>
    </tr>
    <tr>
      <td>
        assertNotSame(Object expected, Object actual) 
      </td>
      <td> 
        두 객체가 동일하지 않은 객체인지 검사한다
      </td>
    </tr>
    <tr>
      <td>
        assertTrue(boolean condition) 
      </td>
      <td> 
        값이 true인지 검사한다
      </td>
    </tr>
    <tr>
      <td>
        assertFalse(boolean condition)
      </td>
      <td> 
        값이 false인지 검사한다
      </td>
    </tr>
    <tr>
      <td>
        assertNull(Object actual) 
      </td>
      <td>
        값이 null인지 검사한다
      </td>
    </tr>
    <tr>
      <td>
        assertNotNull(Object actual) 
      </td>
      <td>
        값이 null이 아닌지 검사한다
      </td>
    </tr>
    <tr>
      <td>
        fail() 
      </td>
      <td>
        테스트를 실패 처리한다
      </td>
    </tr>
    <tr>
      <td>
        assertThrows(Class<T> expectedType, Executable executable) 
      </td>
      <td>
        executable을 실행한 결과로 지정한 타입의 익셉션이 발생하는지 검사한다
      </td>
    </tr>
    <tr>
      <td>
        assertDoesNotThrows(Executable executable) 
      </td>
      <td>
        executable을 실행한 결과로 익셉션이 발생하지 않는지 검사한다
      </td>
    </tr>
  </tbody>
</table>

assert 메서드는 실패하면 다음 코드를 실행하지 않고 바로 익셉션을 발생한다.

일단 모든 검증을 실행하고 그 중에 실패한 것이 있는지 확인하고 싶다면 assertAll() 메서드를 사용하면 된다.

assertAll() 메서드는 Executable 목록을 가변 인자로 전달받아 각 Exectuable을 실행하고 검증에 실패한 코드 목록을 모아서 에러 메시지로 보여준다.

# **테스트 라이프사이클**

JUnit은 각 테스트 메서드마다 다음 순서대로 코드를 실행한다.

1. 테스트 메서드를 포함한 객체 생성
2. (존재하면) @BeforeEach 애노테이션이 붙은 메서드 실행
3. @Test 애노테이션이 붙은 메서드 실행
4. (존재하며) @AfterEach 애노테이션이 붙은 메서드 실행

## **@BeforeEach / @AfterEach**

**@BeforeEach** :

- 테스트를 실행하는데 필요한 준비 작업을 할 때 사용
- 테스트에서 사용할 임시 파일을 생성 또는 테스트 메서드에서 사용할 객체 생성 등

**@AfterEach** :

- 테스트를 실행한 후에 정리할 것이 있을 때 사용
- 테스트에서 사용한 임시 파일을 삭제해야 할 때 등

위 두 메서드는 @Test 애노테이션과 마찬가지로 private 메서드에 사용할 수 없다

## **@BeforeAll / @AfterAll**

**@BeforeAll** :

- 한 클래스의 모든 테스트가 실행되기 전에 특정 작업을 수행해야 할 때 사용
- 정적 메서드에 사용하며 클래스의 모든 테스트 메서드를 실행하기 전에 한 번 실행된다

**@AfterAll** :

- 정적 메서드에 사용하며 모든 테스트 메서드를 실행한 뒤에 실행된다

## **테스트 메서드 간 실행 순서 의존과 필드 공유하지 않기**

JUnit은 테스트 메서드의 실행 순서를 지정하는 방법을 제공하지만, 테스트 메서드가 특정 순서대로 실행된다는 가정하에 테스트 메서드를 작성하면 안 된다.

각 테스트 메서드는 서로 독립적으로 동작해야 한다. 테스트 메서드 간에 의존이 생기면 유지보수를 어렵게 한다. 테스트 코드 역시 코드이므로 유지보수가 중요하다.

# **@DisplayName / @Disabled**

**@DisplayName**

테스트 실행 결과는 테스트 메서드 이름을 사용해서 결과를 표시한다. 자바는 메서드 이름에 공백이나 특수 문자를 사용할 수 없기 때문에 메서드 이름만으로는 내용을 설명하기 부족할 수 있다.

=> @DisplayName 애노테이션을 사용해서 테스트에 표시 이름을 붙일 수 있다.

**@Disabled**

테스트 코드가 완성되지 않았거나 잠시동안 테스트를 실행하지 말아야할 때 @Disable을 클래스나 메서드에 사용하면 테스트 실행 대상에서 제외할 수 있다.

# **모든 테스트 실행하기**

개발 과정에서는 특정 테스트 클래스나 메서드만 실행하지만, 원격 레포지터리나 운영 환경에 코드를 배포하기 전에는 모든 테스트를 실행해서 문제가 없는지 확인해야 한다.

모든 테스트를 실행하는 방법

**Maven** : mvn test (래퍼를 사용하는 경우 mvnw test)

메이븐은 package 단계를 실행할 때 test 단계를 앞서 실행하므로 mvn package 명령어를 실행해도 테스트를 실행한다.

**Gradle** : gradle test (래퍼를 사용하는 경우 gradlew test)

그레이들 역시 build 태스크를 실행하면 테스트를 실행하므로 gradle build 명령어를 실행해도 테스트를 실행한다.
