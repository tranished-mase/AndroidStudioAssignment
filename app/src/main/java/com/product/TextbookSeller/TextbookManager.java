package com.product.TextbookSeller;

import java.util.ArrayList;
import java.util.List;

public class TextbookManager {
    private List<Textbook> inventory;

    public TextbookManager() {
        this.inventory = new ArrayList<>();
    }

    /**
     * Submit and confirm listing while preventing duplicates.
     */
    public String addTextbook(Textbook newBook) {
        // The .contains() method uses the equals() method we just wrote!
        if (inventory.contains(newBook)) {
            return "Error: This textbook is already listed by you.";
        }

        inventory.add(newBook);
        return "Success: Textbook listed for sale!";
    }

    /**
     * Search for textbooks by seller name or book title.
     */
    public List<Textbook> searchBooks(String query) {
        List<Textbook> results = new ArrayList<>();
        for (Textbook book : inventory) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    book.getSellerName().toLowerCase().contains(query.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    // Requirement 1: Browse all available textbooks
    public List<Textbook> getAllBooks() {
        return inventory;
    }
}
