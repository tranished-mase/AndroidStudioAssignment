package com.product.TextbookSeller;

/**
 * Abstract class to provide a base for different types of products.
 */
public abstract class Product implements Sellable {
    protected String title;
    protected String sellerName;

    public Product(String title, String sellerName) {
        this.title = title;
        this.sellerName = sellerName;
    }
    //Getters
    public String getTitle() { return title; }
    public String getSellerName() { return sellerName; }
}