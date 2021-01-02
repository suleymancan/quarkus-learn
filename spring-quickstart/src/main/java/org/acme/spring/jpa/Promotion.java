package org.acme.spring.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Promotion {

    @GeneratedValue
    @Id
    private Long id;

    private String name;

    public Promotion() {
    }

    public Promotion(String name) {
        this.name = name;
    }

}
