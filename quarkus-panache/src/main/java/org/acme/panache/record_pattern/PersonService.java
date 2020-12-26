package org.acme.panache.record_pattern;


import org.acme.panache.PageableDto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@ApplicationScoped
public class PersonService {

    @PostConstruct
    @Transactional
    public void init() {
        IntStream.range(1, 50).forEach(i -> new Person("name" + i, i % 10).persist());
    }

    @Transactional
    public Long save(Person person) {
        person.persist();
        return person.id;
    }


    public List<Person> getAll() {
        return Person.listAll();
    }

    public PageableDto<Person> getPageable(int page, int pageSize) {
        return Person.getPageable(page, pageSize);
    }

    public List<Person> getByAge(Integer age) {
        return Person.getByAge(age);
    }

    public Person getById(Long id) {
        final Optional<Person> byIdOptional = Person.findByIdOptional(id);
        return byIdOptional.orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void update(Long id, Person newPerson) {
        final Person persistPerson = this.getById(id);
        persistPerson.age=newPerson.age;
        persistPerson.name=newPerson.name;
        //persistPerson.update(newPerson);
    }

    @Transactional
    public void delete(Long id) {
        this.getById(id);
        Person.deleteById(id);
    }
}
