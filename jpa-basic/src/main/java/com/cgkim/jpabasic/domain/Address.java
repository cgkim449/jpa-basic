package com.cgkim.jpabasic.domain;

import lombok.Getter;
import javax.persistence.Embeddable;

/**
 * Setter 가 열려있으면 값의 변경 포인트가 너무 많아서, 추적이 어렵다.
 * 그래서 Setter 보다는 비즈니스 메서드로 하는게 좋음
 */
@Getter
@Embeddable //내장타입
public class Address {

    private String city;
    private String street;
    private String zipcode;

    /**
     * JPA 스펙상 엔티티나 임베디드 타입은 자바 기본 생성자를 public 또는 protected 로 설정해야한다.
     * JPA 가 이런 제약을 두는 이유는 JPA 구현 라이브러리가 객체를 생성할 때 리플렉션 같은 기술을 사용할 수 있도록 지원해야하기 때문이다.
     */
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
