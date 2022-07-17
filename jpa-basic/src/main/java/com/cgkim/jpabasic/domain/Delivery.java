package com.cgkim.jpabasic.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter @Setter
@Entity
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    /**
     * ORDINAL
     * - 컬럼에 숫자가 들어감(1,2,3,4,...)
     * - 절대 쓰지말자. 왜냐면 enum 이 중간에 하나 추가 되면 망하는 거니까.
     *  - 예를 들면 현재 READY 는 0?으로 삽입 되고 COMP 는 1로 삽입되고 그럴거임
     *   근데 중간에 enum 이 하나 추가가 되면 망하는 것
     */
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
