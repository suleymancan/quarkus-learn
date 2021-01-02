package org.acme.getting.started;

import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.*;
import io.quarkus.runtime.Startup;
import org.jboss.logging.Logger;

import javax.inject.Singleton;

import static java.time.Duration.ofMillis;

@Startup
@Singleton
public class PromotionRepository {
    private static final Logger LOGGER = Logger.getLogger(PromotionRepository.class);
    private static final int TIMEOUT = 3000;
    private final Collection collection;

    public PromotionRepository(Collection collection) {
        this.collection = collection;
    }

    public void insertPromotion(Promotion promotion) {
       collection.insert(
                String.valueOf(promotion.promotionId),
                promotion,
               InsertOptions.insertOptions().timeout(ofMillis(TIMEOUT))
        );
    }

    public Promotion getPromotion(Long key) {
        try {
            final GetOptions getOptions = GetOptions.getOptions().timeout(ofMillis(TIMEOUT));
            return collection.get(String.valueOf(key), getOptions).contentAs(Promotion.class);
        } catch (DocumentNotFoundException ex) {
            return new Promotion();
        } catch (Exception ex) {
            LOGGER.error(" Exception occurred key: " + key + " exception: ", ex);
            return new Promotion();
        }
    }

    public void deletePromotion(Long key){
        try {
            collection.remove(String.valueOf(key));
        } catch (DocumentNotFoundException ex) {
            LOGGER.error("Document did not exist when trying to remove: " + key);
        }
    }
}
