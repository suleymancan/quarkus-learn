package org.acme;


import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PriceConverter {

    private static final double CONVERSION_RATE = 0.88;

    @Incoming("checkout-promotioncoupon-test")
    @Outgoing("my-data-stream")
    @Broadcast
    public double process(int priceInUsd) {
        System.out.println("incoming:"+priceInUsd);
        return priceInUsd * CONVERSION_RATE;
    }
}
