package br.inatel.promotions.api.promotion.service;

import br.inatel.promotions.api.core.ApiException;
import br.inatel.promotions.api.core.AppErrorCode;
import br.inatel.promotions.api.promotion.PromotionRequest;
import br.inatel.promotions.persistence.promotion.Promotion;
import br.inatel.promotions.persistence.promotion.PromotionRepository;
import org.springframework.stereotype.Service;

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
            throw new ApiException(AppErrorCode.PRODUCTS_QUERY_ERROR);
        }
    }

    public Promotion searchPromotion(String id) throws ApiException {
        return retrievePromotion(id);
    }

    public Promotion createPromotion(PromotionRequest request) {
        var product = buildPromotion(request);
        repository.save(product);
        return product;
    }

    public Promotion updatePromotion(String id, PromotionRequest request) throws ApiException {
        var product = retrievePromotion(id);
        product.setName(request.name());
        product.setStarting(request.starting());
        product.setExpiration(request.expiration());

        //TODO: corrigir set de products
        //product.setProducts(request.products());

        repository.update(product);
        return product;
    }

    public void removePromotion(String id) throws ApiException {
        try {
            repository.delete(id);
        } catch (ExecutionException | InterruptedException e) {
            throw new ApiException(AppErrorCode.PRODUCTS_QUERY_ERROR);
        }
    }

    private Promotion retrievePromotion(String id) throws ApiException {
        try {
            return repository.findById(id)
                    .orElseThrow(() -> new ApiException(AppErrorCode.PRODUCT_NOT_FOUND));
        } catch (ExecutionException | InterruptedException e) {
            throw new ApiException(AppErrorCode.PRODUCTS_QUERY_ERROR);
        }
    }

    private Promotion buildPromotion(PromotionRequest request) {
        var id = UUID.randomUUID().toString();
        return new Promotion(id,
                request.name(),
                request.starting(),
                request.expiration());
                //TODO: corrigir set de products
                //request.products())
    }
}
