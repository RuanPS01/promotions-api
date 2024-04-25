package br.inatel.promotions.persistence.promotion;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Component
public class PromotionFirebaseRepository implements PromotionRepository {

    private static final String COLLECTION_NAME = "promotions";

    private final Firestore firestore;

    public PromotionFirebaseRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    private Promotion buildPromotionFromDocument(DocumentSnapshot documentSnapshot) {
        List<Map<String, Object>> productsMapList = (List<Map<String, Object>>) documentSnapshot.get("products");

        List<ProductDiscount> productDiscountList = new ArrayList<>();
        assert productsMapList != null;
        for (Map<String, Object> productMap : productsMapList) {
            ProductDiscount productDiscount = new ProductDiscount(
                    (String)productMap.get("productId"),
                    (long) productMap.get("discount")
            );
            productDiscountList.add(productDiscount);
        }

        return new Promotion(
                (String) documentSnapshot.get("id"),
                (String) documentSnapshot.get("name"),
                (String) documentSnapshot.get("starting"),
                (String) documentSnapshot.get("expiration"),
                productDiscountList
        );
    }

    @Override
    public void save(Promotion promotion) {
        firestore.collection(COLLECTION_NAME)
                .document(promotion.getId())
                .set(promotion);
    }

    @Override
    public List<Promotion> findAll() throws ExecutionException, InterruptedException {
        return firestore.collection(COLLECTION_NAME)
                .get()
                .get()
                .getDocuments()
                .parallelStream()
                .map(this::buildPromotionFromDocument)
                .toList();
    }

    @Override
    public Optional<Promotion> findById(String id) throws ExecutionException, InterruptedException {
        DocumentSnapshot documentSnapshot = firestore.collection(COLLECTION_NAME)
                .document(id)
                .get()
                .get();

        if (documentSnapshot.exists()) {

            List<Map<String, Object>> productsMapList = (List<Map<String, Object>>) documentSnapshot.get("products");

            List<ProductDiscount> productDiscountList = new ArrayList<>();
            assert productsMapList != null;
            for (Map<String, Object> productMap : productsMapList) {
                ProductDiscount productDiscount = new ProductDiscount(
                        (String)productMap.get("productId"),
                        (long) productMap.get("discount")
                );
                productDiscountList.add(productDiscount);
            }

            Promotion promotion = new Promotion(
                    (String) documentSnapshot.get("id"),
                    (String) documentSnapshot.get("name"),
                    (String) documentSnapshot.get("starting"),
                    (String) documentSnapshot.get("expiration"),
                    productDiscountList
            );

            return Optional.of(promotion);
        } else {
            return Optional.empty();
        }
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
