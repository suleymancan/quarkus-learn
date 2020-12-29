package org.promotion;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class PromotionDeserializer extends JsonbDeserializer<Promotion> {
    public PromotionDeserializer(){
        // pass the class to the parent.
        super(Promotion.class);
    }
}
