package org.acme.resteasyjackson.person;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PersonService {

    @Transactional
    public void save(Person person){
        person.persist();
    }

    public List<Person> getAll(){
        return Person.listAll();
    }

    public Person getById(Integer id){
        return Person.findById(id);
    }

    public void delete(Integer id){
        Person.deleteById(id);
    }
}
