package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order; // 일대일 관계일 경우 FK를 어디에 둬도 상관없으나 영한님은 주로 접근이 많은 곳에 둔다고 함

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // ORDINAL 사용X
    private DeliveryStatus status; // READY, COMP
}
