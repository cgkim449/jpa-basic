package com.cgkim.jpabasic.domain;

import com.cgkim.jpabasic.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    /**
     * 다대다 관계
     * - pk 가 복합키
     * - 이렇게 하면 테이블에 다른 컬럼 추가 못하는 단점이 있음
     */
    @ManyToMany
    @JoinTable(name="category_item", //연결테이블
            joinColumns = @JoinColumn(name="category_id"), //연결테이블의 category_id
            inverseJoinColumns = @JoinColumn(name="item_id") //연결테이블의 item_id
    )
    private List<Item> items = new ArrayList<>();


    //==카테고리 계층 구조==//
    //셀프로 양방향 연관관계를 걸어줌. 그냥 다른 엔티티랑 할때 처럼 똑같이 해주면 됨

    @ManyToOne
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
