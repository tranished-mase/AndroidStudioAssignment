package com.product.TextbookSeller;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

public class TextbookViewModel extends ViewModel {
    private final TextbookManager manager = new TextbookManager();
    private final MutableLiveData<List<Textbook>> _inventory = new MutableLiveData<>();
    private final MutableLiveData<List<Textbook>> _searchResults = new MutableLiveData<>();

    public TextbookViewModel() {
        refreshInventory();
    }

    public LiveData<List<Textbook>> getInventory() {
        return _inventory;
    }

    public LiveData<List<Textbook>> getSearchResults() {
        return _searchResults;
    }

    public void addTextbook(Textbook book) {
        manager.addTextbook(book);
        refreshInventory();
    }

    public void searchBooks(String query) {
        if (query == null || query.trim().isEmpty()) {
            _searchResults.setValue(manager.getAllBooks());
        } else {
            // FIX: Trim the query to avoid trailing space issues
            _searchResults.setValue(manager.searchBooks(query.trim()));
        }
    }

    public void markAsSold(Textbook book) {
        book.markSold();
        refreshInventory();
    }

    public int getTotalBooks() {
        return manager.getAllBooks().size();
    }

    public int getSoldCount() {
        return manager.getSoldCount();
    }

    private void refreshInventory() {
        _inventory.setValue(manager.getAllBooks());
        // Also update search results to show full list if not searching
        _searchResults.setValue(manager.getAllBooks());
    }
}
