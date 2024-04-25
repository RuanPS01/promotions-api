package br.inatel.promotions.api.promotion;

import br.inatel.promotions.persistence.promotion.ProductDiscount;

import java.util.List;

public record PromotionRequest(String id, String name, String starting, String expiration, List<ProductDiscountRequest> products) {
}

