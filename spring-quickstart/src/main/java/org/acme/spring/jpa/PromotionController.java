package org.acme.spring.jpa;


import io.quarkus.runtime.Startup;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    private final PromotionRepository promotionRepository;

    public PromotionController(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @GetMapping
    public ResponseEntity<List<Promotion>> getAll(){
        return ResponseEntity.ok(((List<Promotion>) promotionRepository.findAll()));
    }

    @PostMapping
    public ResponseEntity<Void> save(Promotion promotion) {
        promotionRepository.save(promotion);
        return ResponseEntity.ok()
                .header(HttpHeaders.LOCATION, "/promotions/" + promotion.getId())
                .build();
    }

    @PostConstruct
    @Startup
    public void init() {
        System.out.println("init");
        IntStream.range(1, 10).forEach(i -> promotionRepository.save(new Promotion("test" + i)));
    }
}
