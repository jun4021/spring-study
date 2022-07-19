# Spring 핵심 원리

## 목차
- SOLID
- 의존관계 주입(DI)
- 제어의 역전(IoC)
- 스프링 컨테이너
- 스프링 빈 조회
- 싱글톤 컨테이너
- (별도) JAVA 문법
### 1. SOLID
(1) SRP 단일 책임 원칙
- 한 클래스는 하나의 책임을 가져야 한다.
- 변경이 있을 때 파급 효과가 적으면 잘 적용된 것.

(2) OCP 개방-폐쇄 원칙
- 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
- 다형성을 이용하여 적용.

(3) LSP 리스코프 치환 원칙
- 하위 클래스는 인터페이스의 규약을 지켜야 한다.

(4) ISP 인터페이스 분리 원칙
- 인터페이스를 여러개로 분리해 구현

(5) DIP 의존관계 역전 원칙
- 역할에 집중. 구현에 집중 x
- 클라이언트가 인터페이스에 의존해야 함, 구현체 의존 x

### 2. 의존관계 주입(DI)
- 애플리케이션 실행 시점(런타임)에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서
  클라이언트와 서버의 실제 의존관계가 연결 되는 것을 의존관계 주입이라 한다.
- 의존관계 주입을 사용하면 클라이언트 코드를 변경하지 않고, 클라이언트가 호출하는 대상의 타입
  인스턴스를 변경할 수 있다.
- 의존관계 주입을 사용하면 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를
  쉽게 변경할 수 있다.
  
### 3. 제어의 역전(IoC)
- 프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 제어의 역전(IoC)이라 한다.
- 이때 ```AppConfig```가 이에 해당한다.

### 4. 스프링 컨테이너
- ```ApplicationContext``` 를 스프링 컨테이너라 한다.
- ```@Bean```이라 적힌 메서드를 모드 호출해 스프링 컨테이너에 등록한다.
- 스프링 빈 의존관계를 설정, 주입(DI)한다.

```java
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
```

### 5. 스프링 빈 조회
- 빈 이름으로 조회하기
  - 부모 타입의 빈은 자식 타입도 함께 모두 조회한다.
  - 한 번에 하나의 빈만 조회 가능, 중복 불가 -> 이름으로 조회로 해결(중복된 이름은 불가능하기 때문)
  
```java
AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
MemberService memberService = ac.getBean("memberService", MemberService.class);
```

- 모든 빈 조회하기 
```java
String[] beanDefinitionNames = ac.getBeanDefinitionNames();
```
- 타입으로 모두 조회하기
```java
Map<String, Object> beanOfType = ac.getBeansOfType(Object.class);
```
### 6. 싱글톤 컨테이너
- 서로 다른 클라이언트가 호출할 때마다 새로 객체가 생성된다. 
- 따라서 메모리 낭비를 막기 위해 1개만 생성하고 이를 공유하는 싱글톤 패턴이 필요하다.
#### * 싱글톤 패턴
```java
// static 영역에 1개만 생성
private static final SingletonService instance = new SingletonService();

// 조회 방법
public static SingletonService getInstance(){
    return instance;
}
// 다른 곳에서 new 불가능
private SingletonService(){
}
```
- 클라이언트가 구체 클래스에 의존한다(DIP 위반)
- 테스트하기 어렵다.
- private 생성자로 인해 자식 클래스를 만들기 어렵다.
- 유연하지 못하다.

#### * 싱글톤 컨테이너
- 스프링 컨테이너는 기본적으로 싱글톤 방식으로 동작한다.
- 특정 클라이언트가 공유되는 값을 변경 가능하게 만들면 안된다. (Stateless 상태를 유지)
- ```@Configuration``` 이 싱글톤을 보장해준다.
### (별도) JAVA 문법
- Iterator (for-each)
```java
String[] numbers = {"one", "two", "three"};
for(String number: numbers) {
    System.out.println(number);
}
```

- Lambda