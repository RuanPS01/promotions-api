package br.inatel.promotions.persistence.promotion;

import java.util.*;

//@Component
public class PromotionMemoryRepository implements PromotionRepository {

    private Set<Promotion> db = new HashSet<>();

    @Override
    public void save(Promotion promotion) {
        db.add(promotion);
    }

    @Override
    public List<Promotion> findAll() {
        return db.stream().toList();
    }

    @Override
    public Optional<Promotion> findById(String id) {
        return db.stream()
                .filter(spl -> spl.getId().equals(id))
                .findFirst();
    }

    @Override
    public void delete(String id) {
        db.removeIf(spl -> spl.getId().equals(id));
    }

    @Override
    public void update(Promotion promotion) {
        delete(promotion.getId());
        save(promotion);
    }
}
