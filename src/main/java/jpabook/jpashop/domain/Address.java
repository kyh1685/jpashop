package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embedded
@Getter
public class Address {

    private String city;
    private String streat;
    private String zipcode;
}

