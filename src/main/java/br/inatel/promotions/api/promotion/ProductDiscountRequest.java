package br.inatel.promotions.api.promotion;

public record ProductDiscountRequest(String productId,
                                     long discount) {
}
