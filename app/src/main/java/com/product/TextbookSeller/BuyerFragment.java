package com.product.TextbookSeller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import java.util.List;

public class BuyerFragment extends Fragment {

    private TextbookManager manager;
    private LinearLayout buyerListContainer;

    public static BuyerFragment newInstance(TextbookManager manager) {
        BuyerFragment f = new BuyerFragment();
        f.manager = manager;
        return f;
    }

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_buyer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SearchView searchView   = view.findViewById(R.id.searchView);
        buyerListContainer      = view.findViewById(R.id.buyerListContainer);

        // Show all books initially
        renderBooks(manager.getAllBooks());

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                renderBooks(manager.searchBooks(query));
                return true;
            }
            @Override
            public boolean onQueryTextChange(String text) {
                if (text.isEmpty()) renderBooks(manager.getAllBooks());
                else renderBooks(manager.searchBooks(text));
                return true;
            }
        });
    }

    /** Re-renders whenever the tab is shown so new seller listings appear. */
    @Override
    public void onResume() {
        super.onResume();
        renderBooks(manager.getAllBooks());
    }

    private void renderBooks(List<Textbook> books) {
        if (buyerListContainer == null) return;
        buyerListContainer.removeAllViews();

        if (books.isEmpty()) {
            TextView empty = new TextView(getContext());
            empty.setText("No books found. Try a different search.");
            empty.setTextColor(getResources().getColor(R.color.text_secondary, null));
            empty.setTextSize(14);
            buyerListContainer.addView(empty);
            return;
        }

        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (Textbook b : books) {
            View card = inflater.inflate(R.layout.item_book, buyerListContainer, false);

            ((TextView) card.findViewById(R.id.tvBookTitle)).setText(b.getTitle());
            ((TextView) card.findViewById(R.id.tvPrice)).setText("R" + String.format("%.2f", b.getPrice()));
            ((TextView) card.findViewById(R.id.tvSeller)).setText("Seller: " + b.getSellerName());
            ((TextView) card.findViewById(R.id.tvBank)).setText("Bank: " + b.getBankName() + " · " + b.getAccountType());

            Button btnBuy = card.findViewById(R.id.btnBuy);
            TextView soldBadge = card.findViewById(R.id.tvSoldBadge);

            if (b.isSold()) {
                btnBuy.setVisibility(View.GONE);
                soldBadge.setVisibility(View.VISIBLE);
            } else {
                btnBuy.setVisibility(View.VISIBLE);
                soldBadge.setVisibility(View.GONE);

                btnBuy.setOnClickListener(v -> {
                    b.markSold();
                    btnBuy.setVisibility(View.GONE);
                    soldBadge.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(),
                            "✅ Purchased! Pay " + b.getSellerName()
                            + " at " + b.getBankName()
                            + "\nAcc: " + b.getAccountNumber()
                            + " (" + b.getAccountType() + ")",
                            Toast.LENGTH_LONG).show();
                });
            }

            buyerListContainer.addView(card);
        }
    }
}
