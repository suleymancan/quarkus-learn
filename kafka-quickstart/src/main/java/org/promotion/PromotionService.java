package org.promotion;

import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Channel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.stream.IntStream;

@ApplicationScoped
public class PromotionService {

    @Inject
    @Channel("generated-promotion")
    Emitter<Promotion> emitterCreatePromotion;

    @Inject
    @Channel("updated-promotion")
    Emitter<Promotion> emitterUpdatePromotion;

    @PostConstruct
    public void init(){
        IntStream.range(1,5).forEach(i -> save(new Promotion("name"+i, i*0.1)));
    }

    @Transactional
    public void save(Promotion promotion){
        promotion.persist();
        emitterCreatePromotion.send(promotion);
    }

    @Transactional
    public void update(Long id, Promotion promotion){
        promotion.updateName(promotion.name, id);
        emitterUpdatePromotion.send(promotion);
    }
}
