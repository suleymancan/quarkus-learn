package org.acme.panache.repository_pattern;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Fruit {

    @GeneratedValue
    @Id
    private Long id;

    private String name;

    private String season;

    public Fruit() {
    }

    public Fruit(String name, String season) {
        this.name = name;
        this.season = season;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
}
