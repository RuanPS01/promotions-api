package br.inatel.promotions.api.promotion.service;

import br.inatel.promotions.api.core.ApiException;
import br.inatel.promotions.api.core.AppErrorCode;
import br.inatel.promotions.api.promotion.ProductDiscountRequest;
import br.inatel.promotions.api.promotion.PromotionRequest;
import br.inatel.promotions.persistence.promotion.ProductDiscount;
import br.inatel.promotions.persistence.promotion.Promotion;
import br.inatel.promotions.persistence.promotion.PromotionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;


@Service
public class PromotionService {

    private final PromotionRepository repository;

    public PromotionService(PromotionRepository repository) {
        this.repository = repository;
    }

    public List<Promotion> searchPromotions() throws ApiException {
        try {
            return repository.findAll();
        } catch (ExecutionException | InterruptedException e) {
            throw new ApiException(AppErrorCode.PROMOTIONS_QUERY_ERROR);
        }
    }

    public List<Promotion> searchValidPromotions() throws ApiException {
        try {
            var allPromotions = repository.findAll();
            var now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return allPromotions.stream()
                    .filter(promotion -> {
                        LocalDate starting = LocalDate.parse(promotion.getStarting(), formatter);
                        LocalDate expiration = LocalDate.parse(promotion.getExpiration(), formatter);
                        return (starting.isBefore(now) || starting.isEqual(now)) && (expiration.isAfter(now) || expiration.isEqual(now));
                    }).toList();
        } catch (ExecutionException | InterruptedException e) {
            throw new ApiException(AppErrorCode.PROMOTIONS_QUERY_ERROR);
        }
    }

    public Promotion searchPromotion(String id) throws ApiException {
        return retrievePromotion(id);
    }

    public Promotion searchValidPromotion(String id) throws ApiException {
        var promotion = retrievePromotion(id);

        var now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate starting = LocalDate.parse(promotion.getStarting(), formatter);
        LocalDate expiration = LocalDate.parse(promotion.getExpiration(), formatter);

        if((starting.isBefore(now) || starting.isEqual(now)) && (expiration.isAfter(now) || expiration.isEqual(now))){
            return promotion;
        } else {
            throw new ApiException(AppErrorCode.PROMOTION_NOT_VALID);
        }
    }

    public Promotion createPromotion(PromotionRequest request) {
        var product = buildPromotion(request);
        repository.save(product);
        return product;
    }

    public Promotion updatePromotion(String id, PromotionRequest request) throws ApiException {
        var promotion = retrievePromotion(id);
        promotion.setName(request.name());
        promotion.setStarting(request.starting());
        promotion.setExpiration(request.expiration());
        List<ProductDiscount> products = this.convertProductsOfRequest(request.products());
        promotion.setProducts(products);

        repository.update(promotion);
        return promotion;
    }

    public void removePromotion(String id) throws ApiException {
        try {
            repository.delete(id);
        } catch (ExecutionException | InterruptedException e) {
            throw new ApiException(AppErrorCode.PROMOTIONS_QUERY_ERROR);
        }
    }

    private Promotion retrievePromotion(String id) throws ApiException {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new ApiException(AppErrorCode.PROMOTION_NOT_FOUND));
        } catch (ExecutionException | InterruptedException e) {
            throw new ApiException(AppErrorCode.PROMOTIONS_QUERY_ERROR);
        }
    }

    private Promotion buildPromotion(PromotionRequest request) {
        var id = UUID.randomUUID().toString();
        List<ProductDiscount> products = this.convertProductsOfRequest(request.products());
        return new Promotion(id, request.name(), request.starting(), request.expiration(), products);
    }

    private List<ProductDiscount> convertProductsOfRequest(List<ProductDiscountRequest> productsOfRequest) {
        List<ProductDiscount> products = new ArrayList<>();
        for (ProductDiscountRequest request : productsOfRequest) {
            ProductDiscount productDiscount = new ProductDiscount(
                    request.productId(),
                    request.discount()
            );
            products.add(productDiscount);
        }
        return products;
    }

}
