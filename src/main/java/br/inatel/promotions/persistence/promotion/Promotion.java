package br.inatel.promotions.persistence.promotion;

public class Promotion {
    private String id;
    private String name;
    private String starting;
    private String expiration;
    private ProductDiscount[] products;

    public Promotion(String id, String name, String starting, String expiration) {
        this.id = id;
        this.name = name;
        this.starting = starting;
        this.expiration = expiration;
        //TODO: corrigir set de products
        //this.products = products;
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

    public ProductDiscount[] getProducts() { return products; }
    public void setProducts(ProductDiscount[] value) { this.products = value; }
}
