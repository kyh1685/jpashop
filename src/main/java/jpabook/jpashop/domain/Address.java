package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
public class Address {

    private String city;
    private String streat;
    private String zipcode;
    
    // 기본 생성자 필수
    protected Address(){
        
    }

    public Address(String city, String streat, String zipcode) {
        this.city = city;
        this.streat = streat;
        this.zipcode = zipcode;
    }
}

