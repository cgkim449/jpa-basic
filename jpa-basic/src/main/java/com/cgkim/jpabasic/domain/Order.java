package com.cgkim.jpabasic.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Table(name="orders")
@Entity
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    /**
     * 1대1 관계에서는 fk 가 어느 테이블에 있든 상관없으나, 데이터에 접근 할 일이 많은 테이블에 두는 것이 더 좋을 것이다
     */
    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;//Date 를 사용한다면 날짜 관련 애노테이션을 사용해야함. 근데 LocalDateTime을 사용하면 하이버네이트가 알아서 지원해줌

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
