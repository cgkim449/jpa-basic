# jpa-basic
- 김영한 JPA 정리

## 1. JPA 란, JPA 를 사용해야 하는 이유
> 귀찮은 문제(DAO 작성 등)는 이제 JPA 에게 맞기고 더 좋은 객체 모델링과 더 많은 테스트를 작성하는데 우리의 시간을 보내자

1. JPA(Java Persistence API) 란
    - 자바 진영의 ORM 프레임워크의 표준이다
        - ORM 프레임워크에는 Hibernate, EclipseLink 등이 있다.
        - ORM(Object-Relational Mapping) 이란
            - 객체와 관계형 데이터베이스를 매핑하는 것이다
            - 객체와 관계형 데이터베이스를 매핑해서 패러다임 불일치 문제를 개발자 대신 해결해준다
                - 우리가 할일은 
                    1. 애플리케이션은 객체지향적으로, 데이터베이스는 관계형 데이터베이스대로 설계한다.
                    2. 객체와 데이터베이스 매핑 방법만 ORM 에게 알려준다
                - 그럼 매핑 및 패러다임 불일치 해결은 ORM 이 해준다.
            - 객체는 마치 **컬렉션**에 저장되듯이 ORM 프레임워크에 저장된다. 그럼 ORM 프레임워크가 적절한 insert sql 을 생성해서 데이터베이스에 객체를 저장해준다
    - 애플리케이션과 JDBC 사이에서 동작한다
        - 애플리케이션이 JPA 를 사용하고 JPA 는 JDC 를 사용한다

2. JPA 를 사용해야하는 이유
    1. 생산성이 높다.
        - SQL 작성을 안해도 되기 때문이다.
            - 우리는 **컬렉션** API로 데이터를 다루는 것처럼 데이터를 관리하면 된다.
    2. 유지보수에 좋다.
        1. SQL 수정을 안해도 되기 때문이다. 또한 SQL 의 결과를 객체와 매핑하는 코드도 수정 안해도 되기 때문이다.
            - JPA 를 사용하지 않는다면, 테이블에 컬럼을 하나만 추가해도 관련된 모든 SQL 을 수정해야하며, select 결과를 매핑하는 코드도 모두 변경해야한다. 
            - 우리는 **컬렉션** API로 데이터를 다루는 것처럼 데이터를 관리하면 된다.
        2. 도메인을 객체지향적으로 설계할 수 있기 때문이다.
            - 패러다임 불일치 해결하기 때문에 이게 가능하다.
                - JPA 를 사용하지 않아도 원한다면 도메인을 얼마든지 객체지향적으로 설계할 수 있으나, 객체지향적으로 설계할 수록 패러다임 불일치를 해결하기 위한 코드가 늘어난다.
    3. SQL 중심이 아닌 객체 중심의 개발을 할 수 있기 때문에 생기는 장점이 있다.
        1. 테스트를 작성하기 편리하다. 그래서 버그도 많이 줄어든다.
            - SQL 이 아닌 객체 중심으로 개발하니 테스트를 작성하기도 편리하다.
            - TODO: JPA의 장점? 도메인 중심의 장점?
        2. mybatis 를 사용했을때, 객체 그래프 탐색을 할때 null 문제가 발생하는 등 만약 버그가 있다면, 결국 DAO 까지가서 **어떤 SQL 이 실행되는지 확인해야** 버그가 해결되는 경우가 많다. 
            - 이것은 진정한 의미의 계층 분할이 아니다
            - 물리적으로 SQL 과 JDBC API 를 데이터 접근 계층에 숨기는데 성공했을지는 몰라도 논리적으로는 엔티티와 아주 강한 의존 관계를 가지고 있다. 이런 강한 의존 관계 때문에 회원을 조회할때는 물론이고 회원 객체에 필드를 하나 추가할 때도 DAO 의 CRUD 코드와 SQL 대부분을 변경해야 하는 문제가 발생한다.
            - 지금처럼 **SQL 에 모든 것을 의존**하는 상황에서는 개발자들이 엔티티를 신뢰하고 사용할 수 없다. 
            
    4. 패러다임 불일치를 해결하기 때문에 생기는 장점이 있다.
        - JPA 는 객체간의 관계(상속, 연관), 객체 그래프 탐색, 비교하기와 같은 패러다임 불일치를 해결해준다.
            1. 객체간의 관계
                - 바로 위에서 다뤘듯이, 패러다임 불일치를 해결하기 때문에 도메인을 객체지향적으로 설계할 수 있어 유지보수에 좋다.
                    1. 상속
                        - 
                    2. 연관
            2. 객체 그래프 탐색
                - TODO
            3. 비교하기
                - TODO
    5. 그냥 SQL 만 사용하는 것 보다(mybatis 를 사용하는 것 보다) **성능을 높일 수 있다.**
        1. **DB 와의 통신 횟수를 줄일 수 있기** 때문이다.
            1. 영속성 컨텍스트는 애플리케이션과 데이터베이스 사이에 있는 중간 계층이라 버퍼링, 캐싱을 이용할 수 있기 때문이다(중간 계층이란건 언제나? 버퍼링, 캐싱을 이용할 수 있다). 버퍼링, 캐싱은 모두 DB 와의 통신 횟수를 줄일 수 있다.
                1. 버퍼링
                    - JPA 를 사용하면 JDBC 의 SQL 배치 기능의 설정이 쉽다.
                        - JDBC 의 SQL 배치를 설정하는 코드는 원래 복잡하지만, JPA 는 옵션 하나만 켜주면 간단하게 이 기능을 사용할 수 있다
                            - TODO: 프로퍼티에 쓰는 것 말고 옵션 켰다 끌 수 있는 것 찾기
                        - JDBC SQL 배치는 비슷한 SQL 문들을 하나의 SQL 문으로 만들기 때문에 DB 와의 통신횟수를 줄일 수 있다.
                2. 캐싱
                    1. 1차 캐시
                        - 1차 캐시를 사용하면 DB 와의 통신횟수를 줄일 수 있다.
                            - select 결과를 1차 캐시에 보관하고, 이 후에 데이터베이스에서 조회해오는 게 아니라 먼저 1차 캐시에 존재하는지 찾는다. 있다면 그 객체를 반환해 객체를 **재사용**한다.
                                - mybatis 를 사용한다면 DB 와 2번 통신했을 것이다.
                            - 사실 1차 캐시는 크게 성능상의 이점을 주지 못한다. 왜냐하면 여러 사용자들이 사용하는 캐시가 아니기 때문이다. TODO
                    2. 2차 캐시
                        - 여러 사용자들이 사용하는 캐시이다.
                        - TODO
            2. 그 외에도 **중간 계층이라서** 성능 최적화 관점에서 **시도해볼 수 있는 것들이 많다**?
                - TODO
        2. SQL 힌트도 넣을 수 있다.
            - 하이버네이트는 SQL 힌트를 넣을 수 있는 기능도 제공한다
            - SQL 힌트 가 뭐임 TODO
    6. 데이터 접근 계층(DAO)을 추상화했기 때문에 다른 데이터베이스로 교체하는게 쉽다.
        - JPA 를 사용하지 않으면, 애플리케이션은 처음 선택한 데이터베이스 기술에 종속된다. 다른 데이터베이스로 변경하기가 매우 어렵다(관계형 데이터베이스는 같은 기능도 벤더마다 사용법이 다른 경우가 많기 때문이다(예: 페이징))
            - JPA 는 애플리케이션과 데이터베이스 사이에 추상화된 데이터베이스 접근 계층(DAO?)을 제공해서 애플리케이션이 특정 데이터베이스 기술에 종속되지 않도록 한다. 만약 데이터베이스를 변경하면 JPA 에게 다른 데이터베이스를 사용한다고 알려주기만 하면 된다.
                - ![image](https://user-images.githubusercontent.com/68311318/179433187-a6250e4a-3465-4fa6-8f85-8b6e4096f536.png)
    7. 표준
        - 자바 진영의 ORM 프레임워크의 표준이다. 다른 프레임워크로 교체가 쉽다.
