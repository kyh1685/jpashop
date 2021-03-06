package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    // Order 클래스의 member 필드에 의해서 맵핑된다는 뜻(읽기 전용)
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
