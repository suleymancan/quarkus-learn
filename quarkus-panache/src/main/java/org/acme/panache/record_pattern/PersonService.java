package org.acme.panache.record_pattern;


import io.quarkus.qute.i18n.Localized;
import io.quarkus.qute.i18n.MessageBundles;
import org.acme.exception.AppMessages;
import org.acme.exception.BusinessException;
import org.acme.panache.PageableDto;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

@ApplicationScoped
public class PersonService {
    private static final Logger LOGGER = Logger.getLogger(PersonService.class);
    private AtomicLong counter = new AtomicLong(0);

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
    //CircuitBreaker.failureRatio is by default 0.5, and CircuitBreaker.delay is by default 5 seconds.
    //That means that a circuit breaker will open when 2 of the last 4 invocations failed and it will stay open for 5 seconds.
    @CircuitBreaker(requestVolumeThreshold = 4)
    @Fallback(fallbackMethod = "fallbackGetPageable")
    public PageableDto<Person> getPageable(int page, int pageSize) {
        maybeFailCircuitBreaker();
        LOGGER.info("getPageable working");
        return Person.getPageable(page, pageSize);
    }


    private void maybeFailCircuitBreaker() {
        final Long invocationNumber = counter.getAndIncrement();
        if (invocationNumber % 4 > 1) { // alternate 2 successful and 2 failing invocations
            throw new RuntimeException("Service failed.");
        }
    }

    public PageableDto<Person> fallbackGetPageable(int page, int pageSize){
        LOGGER.info("fallbackGetPageable working");
        return new PageableDto<>(new ArrayList<>(), 0l, 0l);
    }

    public List<Person> getByAge(Integer age) {
        return Person.getByAge(age);
    }

    @Retry(maxRetries = 4)
    @Fallback(fallbackMethod = "fallbackGetById")
    public Person getById(Long id) {
        LOGGER.info("getById called");
        if(id > 500){
            throw new BusinessException("GenericError", MessageBundles.get(AppMessages.class, Localized.Literal.of("en")).genericError());
        }
        final Optional<Person> byIdOptional = Person.findByIdOptional(id);
        maybeFail();
        return byIdOptional.orElseThrow(NotFoundException::new);
    }

    public Person fallbackGetById(Long id){
        LOGGER.info("Falling back to getById#Person");
        return new Person();
    }

    private void maybeFail() {
        if (new Random().nextBoolean()) {
            LOGGER.error("getById#people() invocation failed");
            throw new RuntimeException("Resource failure.");
        }
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
