package br.inatel.promotions.persistence.promotion;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface PromotionRepository {
    void save(Promotion promotion);

    List<Promotion> findAll() throws ExecutionException, InterruptedException;

    Optional<Promotion> findById(String id) throws ExecutionException, InterruptedException;

    void delete(String id) throws ExecutionException, InterruptedException;

    void update(Promotion promotion);
}
