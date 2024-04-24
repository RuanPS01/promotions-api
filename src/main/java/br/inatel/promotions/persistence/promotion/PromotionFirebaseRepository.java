package br.inatel.promotions.persistence.promotion;

import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Component
public class PromotionFirebaseRepository implements PromotionRepository {

    private static final String COLLECTION_NAME = "products";

    private final Firestore firestore;

    public PromotionFirebaseRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public void save(Promotion product) {
        firestore.collection(COLLECTION_NAME)
                .document(product.getId())
                .set(product);
    }

    @Override
    public List<Promotion> findAll() throws ExecutionException, InterruptedException {
        return firestore.collection(COLLECTION_NAME)
                .get()
                .get()
                .getDocuments()
                .parallelStream()
                .map(product -> product.toObject(Promotion.class))
                .toList();
    }

    @Override
    public Optional<Promotion> findById(String id) throws ExecutionException, InterruptedException {
        var product = firestore.collection(COLLECTION_NAME)
                .document(id)
                .get()
                .get()
                .toObject(Promotion.class);
        return Optional.ofNullable(product);
    }

    @Override
    public void delete(String id) throws ExecutionException, InterruptedException {
        firestore.collection(COLLECTION_NAME).document(id).delete().get();
    }

    @Override
    public void update(Promotion product) {
       save(product);
    }
}
