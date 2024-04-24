package br.inatel.promotions.api.promotion.controller;

import br.inatel.promotions.api.core.ApiException;
import br.inatel.promotions.api.promotion.PromotionRequest;
import br.inatel.promotions.api.promotion.service.PromotionService;
import br.inatel.promotions.persistence.promotion.Promotion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/promotions
@RestController
@RequestMapping("/promotions")
public class PromotionController {

    private static final Logger log = LoggerFactory.getLogger(PromotionController.class);

    private final PromotionService service;

    public PromotionController(PromotionService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Promotion>> getPromotions() throws ApiException {
        log.debug("Getting all promotions");
        var promotions = service.searchPromotions();
        return ResponseEntity.ok(promotions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promotion> getPromotion(@PathVariable("id") String id) throws ApiException {
        log.debug("Getting the product by id: " + id);
        var promotion = service.searchPromotion(id);
        return ResponseEntity.ok(promotion);
    }

    @PostMapping("")
    public ResponseEntity<Promotion> postPromotion(@RequestBody PromotionRequest request) {
        var promotion = service.createPromotion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(promotion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promotion> putPromotion(@PathVariable("id") String id,
                                              @RequestBody PromotionRequest request) throws ApiException {
        var promotion = service.updatePromotion(id, request);
        return ResponseEntity.ok(promotion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePromotion(@PathVariable("id")String id) throws ApiException {
        service.removePromotion(id);
        return ResponseEntity.noContent().build();
    }
}
