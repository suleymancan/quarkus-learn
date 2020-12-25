package org.acme.resteasyjackson.person;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Person extends PanacheEntity {

    private String name;


    public static Person findByName(String name) {
        return find("name", name).firstResult();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
