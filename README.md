# 1. 좋은 객체 지향 프로그래밍이란?

 + 클라이언트를 변경하지 않고, 서버의 구현 기능을 유연하게 변경할 수 있는 형태
 + 다형성이 가장 중요하다.

# 2. 좋은 객체 지향 설계의 5가지 원칙(SOLID)

1. SRP 단일 책임 원칙
   + 한 클래스는 하나의 책임만 가져야 한다.
   + 책임이란 기능을 구현할 때 한 클래스 안에 여러가지의 기능이 구현되어있는 형태라면 여러가지의 책임이 있는 형태이다.
   + 만약 여러가지의 책임이 있더라도, 변경이 필요할 때 파급 효과(변경사항 다수)가 적다면 단일 책임 원칙을 잘 따른 것 이다.
2. OCP 개방-폐쇄 원칙
   + 기존 코드를 변경하지 않고 새로운 형태가 추가되는건 가능하다.
   + 단, 변경을 통해 아예 새로운 형태로 만들어 지는건 좋지 않다.
3. LSP 리스코프 치환 원칙
   + 기능 보장이 필요하다.
   + 해당 기능에 맞는 액션을 취해야 하는데 다른 액션을 취할 경우 에러로 간주한다.
4. ISP 인터페이스 분리 원칙
   + 인터페이스를 분할하는것이 좋다.
   + 하나의 인터페이스를 사용자가 사용하면 각 용도의 모든 인터페이스가 작동된다.
   + 그런데 인터페이스의 용도를 나누어 놓게 된다면 필요한 인터페이스를 작동할 때 다른 인터페이스는 작동이 되지 않는다.
   + 그러므로 명확하게 확인이 가능하며 여러가지 추가 인터페이스를 작업하기도 용이하다.
5. DIP 의존관계 역전 원칙
   + 인터페이스에 의존해야 한다.
   + 인터페이스가 아닌 사용자에 치중하게 된다면 사용자가 수정될 경우 인터페이스도 형태가 변경될 수 있다.

다형성만으로는 OCP, DIP를 지킬 수 없습니다.

# 3. 회원 도메인 설계의 문제점

강의에서 문제점에 대해 고민해보라는 사항이 총 두가지가 있었습니다.

1. 다른 저장소로 변경 시 OCP 원칙이 잘 준수 되었는가?
2. DIP를 잘 지키고 있는가?

뒤에 강의에서 추가적으로 설명을 해준다고 하였지만 현재 고민을 해보았다..

1번은 현재는 회원의 형태로 사용 하기 위해서 작성되었고, 회원과 관련된 작업들이 추가된다면 가능하다. 
그런데 아예 새로운 형태, 예를 들어 회원 도메인이 아닌 새로운 형태로 가게 될 경우(아직 어떤 형태가 새로운지는 잘 모르겠다..) 이 형태는 아예 새로이 그려져야 한다. 
그렇다보니 OCP 원칙을 준수하고 있지 않다.
OCP의 경우 기존 코드에 새로운 형태가 추가되는건 가능하지만 추가가 되면서 변경이 되거나 새로운 형태로 만드는 작업이 필요한 경우는 좋지 않은것이다.

2번은 현재는 회원의 등급이 일반과 VIP 두가지 형태로 나누어져 있다.
그런데 회원확인을 여러가지 더 추가되면서 등급도 추가되며 정보가 수정된다면 인터페이스의 형태도 충분히 변경이 가능해진다.
이런 경우엔 DIP 원칙을 준수하지 않게 된다.

예시 부분은 뒤에서 추가로 강의를 듣게 된다면 알 수 있을 것 같다.

# 4. 새로운 할인 정책 적용과 문제점

현재 이 강의에서는 추상은 할인정책을 표현하고, 구체는 할인청책의 방법을 나타낸다.

구체에 의존하지 말고, 추상에 의존하여야 한다. Fix를 바라보던 형태에서 Rate로 수정이 될 때 OrderServiceImpl의 소스를 수정해야 하니 DIP와 OCP를 위반하는 경우이다.

# 5. 관심사의 분리

해당 Impl에 생성하는 형태가 아닌 새로운 파일에 필요한 상황에서 불러올 수 있도록 AppConfig.java라는 생성자를 생성하여 작업을 진행한다.

Impl은 정말 실행에만 집중하며, 다른 상황에 대해서는 작동하지 않도록 하는게 가장 중요하다.

# 6. IoC, DI, 그리고 컨테이너

제어의 역전(IoC)은 제어의 역전으로 제어의 흐름을 직접 제어하는것이 아닌, 외부에서 관리하는걸 말한다.

프레임워크 - 내가 작성한 코드를 제어하고 대신 실행시킴

라이브러리 - 내가 작성한 코드가 제어의 흐름을 직접 담당

의존관계 주입(DI)은 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 수정할 수 있는걸 말한다.

AppConfig를 사용함으로써 작업을 처리해주는 정적인 클래스들은 수정하지 않고 AppConfig를 통해 동적인 객체 인스턴스 의존관계를 수정 할 수 있다.

AppConfig를 주로 DI 컨테이너, 어샘블러, 오브젝트 팩토리 등으로 불린다.

# 7. 스프링으로 변환하기

기존엔s AppConfig를 통해서 직접 조회했었다. 그런데 Spring을 사용한다면 다르게 사용 할 수 있다.

스프링 컨테이너를 통해서 객체를 스프링 빈으로 등록하고 스프링 컨테이너를 통해서 스프링 빈을 찾아서 사용 할 수 있다.

# 8. 스프링 컨테이너 생성

1. 빈 이름은 매서드 이름을 사용한다.
2. 빈 이름을 직접 부여할 수 있다.
3. 중복으로 되어있을 경우 다른 빈들이 무시되거나 기존 빈을 덮는 등 설정에 따라 오류가 발생할 수 있다.

# 9. 스프링 빈
