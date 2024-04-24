package br.inatel.promotions.persistence.promotion;

public class ProductDiscount {
    private String productID;
    private long discount;

    public String getProductID() { return productID; }
    public void setProductID(String value) { this.productID = value; }

    public long getDiscount() { return discount; }
    public void setDiscount(long value) { this.discount = value; }
}
