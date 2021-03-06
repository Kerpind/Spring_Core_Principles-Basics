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
 
1. 빈 이름은 메서드 이름을 사용한다.
2. 빈 이름을 직접 부여할 수 있다.
3. 중복으로 되어있을 경우 다른 빈들이 무시되거나 기존 빈을 덮는 등 설정에 따라 오류가 발생할 수 있다.

# 9. 컨테이너에 등록된 모든 빈 조회

1. JUnit5 부터는 public 설정을 하지 않아도 됩니다.

# 10. 동일한 타입이 둘 이상

1. 이름이 같을 경우 에러가 발생합니다.
2. 그 에러를 해결해주기 위한 방법으로는 빈 이름을 지정해주는 것 입니다.
3. 만약 특정 타입을 모두 조회하는 방법을 하고싶다면 Map에 담아서 가져오는 방법도 있습니다.

# 11. 상속 관계

1. 부모타입으로 조회하면 자식 타입도 함께 조회한다.
2. extend가 숨겨져 있는거라고 생각하면 쉽다.

# 12. BeanFactory

1. 스프링 컨테이너의 최상위 인터페이스이다.
2. 스프링 빈을 관리하고 조회하는 역할을 한다.
3. ApplicationContext가 BeanFactory 기능을 모두 상속받아서 제공한다.
4. ApplicationContext가 제공하는 부가기능 중 대표적인 4가지가 있다.
   + MessageSource - 국제화 기능이 가능하다.(한국서버는 한국어, 영어서버는 영어로 출력)
   + EnvironmentCapable - 로컬, 개발, 운영 등의 환경변수를 구분해서 처리가 가능하다.
   + ApplicationEventPublisher - 이벤트를 발행하고 구독하는 모델을 편리하게 지원 가능하다.
   + ResourceLoader - 파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회할 수 있다.

# 13. 웹 애플리케이션과 싱글톤

1. DI 컨테이너인 AppConfig는 요청할 때 마다 객체를 새로 생성한다.
2. 해결 방안은 객체가 1개만 생성하도록 설정하고 그걸 공유하도록 하면 된다.

# 14. 싱글톤 패턴

1. static 영역에 final로 생성해서 작업해두면 된다.
2. new로도 만들 수 없게 private로 호출도 막아둔다.
3. 단점으로는 구현하는 코드 자체가 많이 들어가게 된다.
4. DIP와 OCP를 위반하게 된다.

# 15. 싱글톤 컨테이너

1. 스프링 컨테이너 때문에 객체를 공유해서 효율적으로 사용이 가능하다.
2. 99.9% 스프링은 싱글톤을 사용한다.

# 16. 싱글톤 방식의 주의점

1. 같은걸 사용하기 때문에 여러 사용자가 호출을 할 경우 데이터가 덮어질 수 있다.
2. 테스트 예시로 10000원이 들어가야 하는데 20000원으로 덮어지는 이유가 그런 이유다.
3. 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다.
4. 그렇다보니 무상태(지역변수, 파라미터, ThreadLocal(?))로 반드시 설계를 해야한다!

# 17. @Configuration과 싱글톤 

1. AppConfig에서도 같은게 여러번 호출 되는데 싱글톤 방식이 아니지 않나? 에 대한 테스트
2. 테스트를 해본 결과 한번씩만 호출되는 sout을 볼 수 있었다.


# 18. @Configuration과 바이트코드 조작의 마법

1. @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
2. 싱글톤 보장을 작업해주는 것이다.
3. 만약 @Configuration을 빼게 된다면 여러번 호출되게 된다.
4. 싱글톤이 깨지게 된 것이다.

# 19. 컴포넌트 스캔과 의존관계 자동 주입 시작하기

1. 여러 코드에 @Bean을 작업하고 하는게 많이 어렵다.
2. 그렇다보니 @Component 와 @Autowired 를 통해서 @Bean 작업을 대신 해줄 수 있다.
3. 먼저 @ComponentScan 을 통해서 @Component 가 붙은 모든 클래스를 스프링 빈으로 등록한다.
4. 스프링 빈의 이름을 클래스명을 사용하되 맨 앞글자는 소문자를 사용하여 생성한다.
5. 그 상태에서 생성자에 @Autowired 를 지정하면 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입하게 된다.

# 20. 탐색 위치와 기본 스캔 대상

1. basePackages 를 통해서 탐색할 패키지의 시작 위치를 지정할 수 있다.
2. basePackageClasses 를 통해서 지정한 클래스의 패키지를 탐색 시작 위치로도 지정할 수 있다.
3. 디폴트로는 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
4. 프로젝트 시작 루트에 메인 설정 정보를 두는걸 권장한다.

# 21. 필터

1. 필터를 통해서 스프링 빈에 등록여부를 결정할 수 있다.
2. includeFilters 와 excludeFilters 가 있다.

# 22. 중복 등록과 충돌

1. 자동 빈 등록 VS 자동 빈 등록 의 경우 Exception 이 발생한다.
2. 수동 빈 등록 VS 자동 빈 등록 의 경우엔 수동 빈 등록이 우선권을 가져간다.
3. 수동 빈 등록 시 로그가 표시가 된다.
   (Overriding bean definition for bean 'memoryMemberRepository' with a different definition: replacing)
4. 최근 스프링 부트에선 수동 빈과 자동 빈이 충돌이 나면 에러를 발생하도록 변경되었다.
5. application.properties 에서 spring.main.allow-bean-definition-overriding=true 를 등록해서 가능하도록 설정 할 수도 있다.

# 23. 의존관계 주입 방법

1. 생성자 주입
+ 생성자를 통해서 의존 관계를 주입 받는 방법이다.
+ 생성자 호출시점에서 딱 1번만 호출되는 것이 보장된다.
+ 불변 및 필수 의존관계에 사용된다.
+ 불변은 값을 세팅하고 수정을 못하게 하는걸 말한다.
+ 필수는 값이 반드시 있어야 하는 상황을 말한다.
+ 만약 생성자가 단 1개만 있다면 @Autowired 가 자동으로 사용된다.

2. 수정자 주입(?)
+ settter 라 불리는 필드의 값을 변경하는 수정자 메서드(데이터setter 해오는 것들)를 통해서 의존관계를 주입하는 방법이다.
+ 의존관계 주입 후 일어나는 단계이다.

3. 필드 주입
+ 필드에 바로 의존관계를 주입하는 방식이다.
+ 만약 이렇게 하게되면 의존관계가 주입된 데이터가 수정이 안되는 단점이 생긴다.
+ setter 로 하는 형태가 훨씬 낫다.

4. 일반 메서드 주입
+ 메서드에 @Autowired 작업을 하는 것이다.
+ 한번에 여러 필드를 주입받을 수 있다.

>의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈이어야 동작한다.

# 24. 옵션 처리

1. 의존관계가 없을 시 @Autowired 에 required=false 작업시 메서드 자체가 호출되지 않는다.
2. @Nullable 의 경우 값이 null로 들어오게 된다.
3. Optional 은 Optional 에 데이터가 감싸져서 들어오게 된다.

# 25. 생정자 주입을 선택하라!

1. 의존관계 주입이 한번 일어나면 의존관계를 변경할 일이 없다.
2. 애플리케이션 종료 전까지 변하지 않는 불변상태여야 하는게 맞다.
3. 생성자 주입을 사용하면 final 을 사용할 수 있다.

# 26. 롬복과 최신 트렌드

1. 롬복은 플러그인에서 다운로드 받을 수 있는건데 getter, settter 를 대신할 수 있다.
2. @ToString 이란 것도 있는데 이건 모든 출력을 String 으로 변환해주는 기능이다.
3. @RequiredArgsConstructor 라는 기능은 final이 붙은 필드를 모아서 자동으로 생성자를 만들어준다.

# 27. 여러개의 빈이 선택되었을 때 해결 방법

1. @Autowired 필드 명 매칭은 타입 매칭의 결과가 2개 이상일 때 필드 명, 파라미터 명으로 빈 이름을 매칭한다.
2. @Qualifier 는 이름을 지정하여 주입하는 방식이다. 만약 찾지 못하면, 해당 이름으로 스프링 빈을 찾는다.
3. @Primary 는 여러개의 빈 중에서 우선순위를 가장 먼저 가지게 된다.
4. 만약 @Qualifier 와 @Primary 가 같이 되어 있는 경우는 @Qualifier 가 우선순위를 가지게 된다.

# 28. 자동, 수동의 올바른 실무 운영 기준

1. 자동 빈 등록을 사용하여 번거로움에서 벗어나자!
2. 기술 지원 객체는 수동 빈으로 등록하는게 좋다.
3. 자동으로 할 경우 가독성이 어려워지기 때문이다.

# 29. 빈 생명주기 콜백 시작

1. 스프링 빈은 객체 생성 후 의존관계를 주입한다.
2. 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료

# 30. 인터페이스 InitializingBean, DisposableBean

1. 빈 생명주기를 위해 필요한 인터페이스중 하나이다.
2. 초기화, 소멸 인터페이스의 단점은 스프링 전용 인터페이스여서 스프링 전용 인터페이스에 의존하게 되어있다.
3. 이름을 변경 할 수 없으며, 내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다.

# 31. 빈 등록 초기화, 소멸 메서드 지정

1. 메서드의 이름을 자유롭게 정할 수 있다.
2. 스프링 빈이 스프링 코드에 의존하지 않는다.
3. 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있다.
4. destroyMethod 의 경우 기본값이 추론(inferred) 이다. 종료 메서드를 추론해서 호출한다는 것이다.
5. 만약 추론 기능을 사용하기 싫다면 빈 공백으로 두면 된다.

# 32. 애노테이션 @PostConstruct, @PreDestroy

1. 가장 권장하고 많이 사용하는 방식이다.
2. 애노테이션 하나만 붙이면 되므로 매우 편리하다.
3. 스프링이 아닌 다른 컨테이너에서도 동작한다.
4. 컴포넌트 스캔과 쓰기 좋다.
5. 단점으론 외부 라이브러리에는 적용할 수 없다.

# 33. 
# 1. 어노테이션 기능 간단히 정리

## 1.@Configuration

Java Class 위에 선언해줌으로써 Spring 설정 정보와 관련된 파일이란 것을 명시하는 경우 사용한다.

## 2.@Bean

선언을 통해 파라미터로 넘어온 값을 스프링 컨테이너에 저장한다. 빈 이름은 해당 이름으로 저장이 된다. 이름을 변경하려면 선언문 옆에 이름 설정을 해준다.

> 만약, @Configuration이 없이 @Bean을 명시하고 여러번 작성할 경우 그 수만큼 여러번 호출하게 된다. 싱글톤이 보장되지 않는다. \
  @Configuration을 명시하였을 경우 @Bean을 통해서 생성되면 다시 호출되더라도 생성된 빈을 반환한다.

## 3.@ComponentScan

@Bean 명시는 등록이 필요한 모든 파일에 작업을 해야하는 번거로움이 있다. 그래서 다른 방법으로 @ComponentScan과 @Component를 사용하는 법이다.
먼저 설정파일로 사용할 Java Class 위에 선언을 해준다.

## 4.@Component

그리고 @ComponentScan의 대상이 되도록 스피링 빈으로 등록할 Class 위에 @Component어노테이션을 선언해준다.
@Service, @Repository, @Controller 등도 선언할 수 있다.

## 5.@Autowired

생성자에 @Autowired를 지정하면 Spring 컨테이너가 해당 스프링 빈을 찾아서 주입한다. 여러 필드를 주입받을 수 있다. 만약 생성자가 하나라면 생략해도 된다.

## 6.@SpringBootApplication
Spring Boot의 기본적인 설정을 선언해주는 어노테이션 입니다.

@SpringBootConfiguration
> @Configuration 과 동일한 기능. 구성을 자동으로 찾아주는 차이점만 있다.

@ComponentScan
> @Component 어노테이션을 찾아주는 어노테이션이다.

@EnableAutoConfiguration
> Spring Boot의 기본적인 auto configuration 리스트의 클래스들을 @Configuration 없이 빈에 등록될 수 있도록 해준다.

## 7.@RequiredArgsConstructor

final이 붙은 필드를 모아서 자동으로 생성자를 만들어주는 어노테이션이다.

2. GET, POST, JSON 
     저희 회사 소스에서 예시 1나씩만 캡쳐(Request 요청메시지 보이도록)

