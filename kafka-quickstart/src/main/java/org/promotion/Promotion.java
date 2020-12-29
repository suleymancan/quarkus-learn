package org.promotion;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Promotion extends PanacheEntity {

    public String name;

    public Double discount;

    public Promotion() {
    }

    public Promotion(String name, Double discount) {
        this.name = name;
        this.discount = discount;
    }

    public static void updateName(String name, Long id){
        Promotion.update("name=?1 where id=?2", name, id);
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "name='" + name + '\'' +
                ", discount=" + discount +
                '}';
    }
}
