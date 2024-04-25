package br.inatel.promotions.persistence.promotion;

public class ProductDiscount {
    private String productID;
    private long discount;

    public ProductDiscount(String productID, long discount) {
        this.productID = productID;
        this.discount = discount;
    }

    public String getProductId() { return productID; }
    public void setProductId(String value) { this.productID = value; }

    public long getDiscount() { return discount; }
    public void setDiscount(long value) { this.discount = value; }
}
