# jpa-basic
- 김영한 jpa 정리

## 1. jpa(java persisten api)란
- ORM(Object Relataional Mapping)이다.
    - 말그대로 객체지향과 RDBMS를 매핑해준다
    - ORM을 사용하면 우리는 객체지향은 객체지향대로 RDBMS는 RDBMS대로 설계할 수 있다.
        - ORM을 사용하지 않으면 도메인을 객체지향이 아닌 RDBMS에 맞게 설계할 수 밖에 없다.

## 2. jpa를 사용해야 하는 이유
- 유지보수를 위해서이다.
    1. sql
    2. 객체지향 설계를 위해