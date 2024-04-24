package br.inatel.promotions.api.promotion;

import br.inatel.promotions.persistence.promotion.ProductDiscount;

public record PromotionRequest(String id, String name, String starting, String expiration, ProductDiscountRequest[] products) {
}

