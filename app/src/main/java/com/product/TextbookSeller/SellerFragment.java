package com.product.TextbookSeller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import java.util.List;

public class SellerFragment extends Fragment {

    private TextbookViewModel viewModel;
    private LinearLayout listingsContainer;

    public static SellerFragment newInstance(TextbookManager manager) {
        return new SellerFragment();
    }

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_seller, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(TextbookViewModel.class);

        EditText editTitle   = view.findViewById(R.id.editTitle);
        EditText editSeller  = view.findViewById(R.id.editSeller);
        EditText editPrice   = view.findViewById(R.id.editPrice);
        EditText editBank    = view.findViewById(R.id.editBankName);
        EditText editAccNum  = view.findViewById(R.id.editAccNumber);
        EditText editAccType = view.findViewById(R.id.editAccType);
        Button   btnSubmit   = view.findViewById(R.id.btnSubmit);
        listingsContainer    = view.findViewById(R.id.listingsContainer);

        viewModel.getInventory().observe(getViewLifecycleOwner(), this::refreshListings);

        btnSubmit.setOnClickListener(v -> {
            String title  = editTitle.getText().toString().trim();
            String seller = editSeller.getText().toString().trim();
            String priceStr = editPrice.getText().toString().trim();
            String bank   = editBank.getText().toString().trim();
            String accNum = editAccNum.getText().toString().trim();
            String type   = editAccType.getText().toString().trim();

            if (title.isEmpty() || seller.isEmpty() || priceStr.isEmpty()
                    || bank.isEmpty() || accNum.isEmpty() || type.isEmpty()) {
                Toast.makeText(getContext(), "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                return;
            }

            double price;
            try {
                price = Double.parseDouble(priceStr);
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Enter a valid price!", Toast.LENGTH_SHORT).show();
                return;
            }

            Textbook book = new Textbook(title, seller, 1, price, accNum, bank, type);
            viewModel.addTextbook(book);
            Toast.makeText(getContext(), "Success: Textbook listed for sale!", Toast.LENGTH_LONG).show();

            editTitle.setText("");
            editSeller.setText("");
            editPrice.setText("");
            editBank.setText("");
            editAccNum.setText("");
            editAccType.setText("");
        });
    }

    /**Used to refresh the seller's own listing cards. */
    public void refreshListings(List<Textbook> books) {
        if (listingsContainer == null) return;
        listingsContainer.removeAllViews();

        if (books.isEmpty()) {
            TextView empty = new TextView(getContext());
            empty.setText("No listings yet. Add your first book above.");
            empty.setTextColor(getResources().getColor(R.color.text_secondary, null));
            empty.setTextSize(14);
            empty.setPadding(0, 8, 0, 0);
            listingsContainer.addView(empty);
            return;
        }

        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (Textbook b : books) {
            View card = inflater.inflate(R.layout.item_book, listingsContainer, false);

            ((TextView) card.findViewById(R.id.tvBookTitle)).setText(b.getTitle());
            ((TextView) card.findViewById(R.id.tvPrice)).setText("R" + String.format("%.2f", b.getPrice()));
            ((TextView) card.findViewById(R.id.tvSeller)).setText("Seller: " + b.getSellerName());
            ((TextView) card.findViewById(R.id.tvBank)).setText("Bank: " + b.getBankName() + " · " + b.getAccountType());

            // Seller view: show sold badge if sold, hide buy button
            card.findViewById(R.id.btnBuy).setVisibility(View.GONE);
            if (b.isSold()) {
                card.findViewById(R.id.tvSoldBadge).setVisibility(View.VISIBLE);
            }

            listingsContainer.addView(card);
        }
    }
}
