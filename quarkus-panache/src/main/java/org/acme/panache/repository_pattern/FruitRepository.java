package org.acme.panache.repository_pattern;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import org.acme.panache.PageableDto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.IntStream;

// resource: https://quarkus.io/guides/hibernate-orm-panache#solution-2-using-the-repository-pattern
@ApplicationScoped
public class FruitRepository implements PanacheRepository<Fruit> {


    @PostConstruct
    @Transactional
    public void init() {
        IntStream.range(1, 50).forEach(i -> persist(new Fruit("name" + i, "season" + i % 4)));
    }

    public PageableDto<Fruit> getPageable(int page, int pageSize){
        final PanacheQuery<Fruit> fruitPanacheQuery = findAll();
        final List<Fruit> result = fruitPanacheQuery.page(Page.of(page - 1, pageSize)).list();
        return new PageableDto<>(result, fruitPanacheQuery.count(), fruitPanacheQuery.count() / pageSize);
    }

    public void update(Fruit fruit, Long id) {
        update("name=?1, season=?2 where id=?3", fruit.getName(), fruit.getSeason(), id);
    }

    public List<Fruit> getBySeason(String season) {
        return list("season", season);
    }
}
