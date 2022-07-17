package com.cgkim.jpabasic.domain.item;

import com.cgkim.jpabasic.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

/**
 * InheritanceType
 * - SINGLE_TABLE
 *  - 한 테이블에 컬럼을 다 때려박는 것
 * - TABLE_PER_CLASS
 *  - Album, Book, Movie 테이블 만 나오는 전략
 * - JOINED
 *  - 가장 정규화된 스타일
 */
@Getter @Setter
@DiscriminatorColumn(name="dtype") //싱글 테이블 전략에서 사용
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity; //재고 수량

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
