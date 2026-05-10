package com.product.TextbookSeller;
/**
 * Interface defining what it means for an item to be sellable in our application.
 */
public interface Sellable {
    double getPrice();
    void displayDetails();
}
