package com.product.TextbookSeller;
/**
 * Interface defining what it means for an item to be sellable in our app.
 */
public interface Sellable {
    double getPrice();
    void displayDetails();
}
