package org.acme.panache.record_pattern;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import org.acme.panache.PageableDto;
import org.acme.panache.repository_pattern.Fruit;

import javax.persistence.Entity;
import java.util.List;

// resource: https://quarkus.io/guides/hibernate-orm-panache#solution-1-using-the-active-record-pattern
@Entity
public class Person extends PanacheEntity {

    public String name;

    public Integer age;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public static PageableDto<Person> getPageable(int page, int pageSize){
        final PanacheQuery<Person> fruitPanacheQuery = findAll();
        final List<Person> result = fruitPanacheQuery.page(Page.of(page - 1, pageSize)).list();
        return new PageableDto<>(result, fruitPanacheQuery.count(), fruitPanacheQuery.count() / pageSize);
    }


    public static List<Person> getByAge(int age){
        return Person.list("age", age);
    }

    public void update(Person newPerson) {
        Person.update("name=?1, age=?2 where id=?3", newPerson.name, newPerson.age, this.id);
    }

}
