package com.product.TextbookSeller;

import java.util.ArrayList;
import java.util.List;

public class TextbookManager {
    private List<Textbook> inventory;

    public TextbookManager() {
        this.inventory = new ArrayList<>();
    }

    /** Add a textbook, preventing duplicates (same title + seller). */
    public String addTextbook(Textbook newBook) {
        if (inventory.contains(newBook)) {
            return "Error: This textbook is already listed by you.";
        }
        inventory.add(newBook);
        return "Success: Textbook listed for sale!";
    }

    /** Search by title or seller name. */
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

    /** All books in inventory. */
    public List<Textbook> getAllBooks() {
        return inventory;
    }

    /** Count of books that have been sold. */
    public int getSoldCount() {
        int count = 0;
        for (Textbook b : inventory) {
            if (b.isSold()) count++;
        }
        return count;
    }
}
