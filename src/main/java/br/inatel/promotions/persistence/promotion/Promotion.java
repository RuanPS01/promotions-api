package br.inatel.promotions.persistence.promotion;

import java.util.Arrays;
import java.util.List;

//{
//    "id": "uuid",
//    "name": "name",
//    "starting": "starting_date",
//    "expiration": "expiration_date",
//    "products": [
//        {
//            "productId": "product_id",
//            "discount": 15
//        }
//    ]
//}
public class Promotion {
    private String id;
    private String name;
    private String starting;
    private String expiration;
    private List<ProductDiscount> products;

    public Promotion(String id, String name, String starting, String expiration, List<ProductDiscount> products) {
        this.id = id;
        this.name = name;
        this.starting = starting;
        this.expiration = expiration;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getStarting() { return starting; }
    public void setStarting(String value) { this.starting = value; }

    public String getExpiration() { return expiration; }
    public void setExpiration(String value) { this.expiration = value; }

    public List<ProductDiscount> getProducts() { return products; }
    public void setProducts(List<ProductDiscount> value) { this.products = value; }
}
