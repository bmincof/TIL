# **TDD와 기능 명세 / 설계**

기능을 구현하기 위해 기능을 크게 입력과 결과로 나누어 볼 수 있다.

- 입력 : 기능을 실행하는데 필요한 값, 보통 메서드의 파라미터로 전달한다.

- 결과 : 성공 또는 실패, 리턴 값, 익셉션 등 여러 형식으로 정의할 수 있다.

다양한 형태의 요구사항 문서를 이용해서 기능 명세를 구체화하는 동안 입력과 결과를 도출하고 도출한 기능 명세를 코드에 반영한다.

기능 명세의 입력과 결과를 코드에 반영하는 과정에서 기능의 이름, 파라미터, 리턴 타입 등이 결정되고 이는 곧 기능에 대한 설계 과정과 연결된다.

## **필요한 만큼 설계하기**

TDD는 테스트를 통과할 만큼만 코드를 작성한다. 이는 설계에도 동일하게 적용된다. 미리 필요를 예측해서 설계를 유연하게 만들지 않는다.

실제 테스트 사례를 추가하고 통과시키는 과정에서 필요한 만큼 설계를 변경한다.

TDD 개발을 진행하면 현시점에서 테스트를 통과시키는데 필요한 만큼의 코드만 만들게 된다. 지금 시점에서 필요한 설계만 코드에 반영할 가능성이 커지고 설계가 불필요하게 복잡해지는 것을 방지할 수 있다.

## **기능 명세 구체화**

테스트 코드를 작성하기 위해 개발자는 기능 명세를 정리해야 한다. 요구사항 명세 문서는 기능을 구현하기에는 생략된 내용이 많으므로 문서로부터 입력과 결과를 도출해야 한다.

테스트 코드는 구체적인 입력과 결과를 이용해서 작성하므로 개발자는 예를 통해 기능 명세를 구체화하게 된다. **즉, 테스트 코드는 예를 이용한 구체적인 명세가 된다.**
