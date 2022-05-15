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