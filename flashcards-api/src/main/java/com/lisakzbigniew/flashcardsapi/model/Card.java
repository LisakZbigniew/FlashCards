package com.lisakzbigniew.flashcardsapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Card {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @OneToOne private Phrase a;
    @OneToOne private Phrase b;
    

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Phrase getA() {
        return a;
    }
    public void setA(Phrase a) {
        this.a = a;
    }
    public Phrase getB() {
        return b;
    }
    public void setB(Phrase b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", a='" + getA() + "'" +
            ", b='" + getB() + "'" +
            "}";
    }

}
