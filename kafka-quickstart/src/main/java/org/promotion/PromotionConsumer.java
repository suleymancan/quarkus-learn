package org.promotion;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PromotionConsumer {

    @Incoming("checkout-promotioncoupon-test-create")
    public void consumeCreatedPromotion(Promotion promotion){
        System.out.println("created: " +promotion);
    }

    @Incoming("checkout-promotioncoupon-test-update")
    public void consumeUpdatedPromotion(Promotion promotion){
        System.out.println("updated: " +promotion);
    }

}
