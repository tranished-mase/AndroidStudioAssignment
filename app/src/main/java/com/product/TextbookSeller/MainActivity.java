package com.product.TextbookSeller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextbookManager manager = new TextbookManager();
    private TextView textInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Core Input Fields
        EditText editTitle = findViewById(R.id.editTitle);
        EditText editSeller = findViewById(R.id.editSeller);
        EditText editPrice = findViewById(R.id.editPrice);

        // NEW: Specific Banking Input Fields linked to your new XML IDs
        EditText editBankName = findViewById(R.id.editBankName);
        EditText editAccNumber = findViewById(R.id.editAccNumber);
        EditText editAccType = findViewById(R.id.editAccType);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        textInventory = findViewById(R.id.textInventory);
        SearchView searchView = findViewById(R.id.searchView);

        // --- Search Logic [Requirement 4] ---
        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    List<Textbook> results = manager.searchBooks(query);
                    if (results.isEmpty()) {
                        Toast.makeText(MainActivity.this, "No books found for: " + query, Toast.LENGTH_SHORT).show();
                    } else {
                        Textbook match = results.get(0);
                        String info = "Found: " + match.getTitle() + " (R" + String.format("%.2f", match.getPrice()) + ")";
                        Toast.makeText(MainActivity.this, info, Toast.LENGTH_LONG).show();
                    }
                    return true;
                }
                @Override public boolean onQueryTextChange(String newText) { return false; }
            });
        }

        // --- Submit Logic [Requirement 2 & 3] ---
        btnSubmit.setOnClickListener(v -> {
            try {
                String title = editTitle.getText().toString();
                String seller = editSeller.getText().toString();
                double price = Double.parseDouble(editPrice.getText().toString());

                // NEW: Extract values from the specific bank fields
                String bank = editBankName.getText().toString();
                String accNum = editAccNumber.getText().toString();
                String type = editAccType.getText().toString();

                // Validation: Check if any bank field is empty
                if(bank.isEmpty() || accNum.isEmpty() || type.isEmpty()) {
                    Toast.makeText(this, "Please fill in all banking details!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create book with updated parameter list (7 parameters)
                Textbook book = new Textbook(title, seller, 1, price, accNum, bank, type);
                String response = manager.addTextbook(book);

                Toast.makeText(this, response, Toast.LENGTH_LONG).show();

                if(response.contains("Success")) {
                    // Clear all fields for a smooth user experience
                    editTitle.setText("");
                    editSeller.setText("");
                    editPrice.setText("");
                    editBankName.setText("");
                    editAccNumber.setText("");
                    editAccType.setText("");
                    refreshBookList(textInventory);
                }
            } catch (Exception e) {
                Toast.makeText(this, "Check your inputs (ensure price is a number)!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // --- Browse Display [Requirement 1] ---
    private void refreshBookList(TextView display) {
        StringBuilder builder = new StringBuilder("Current Inventory:\n\n");
        for (Textbook b : manager.getAllBooks()) {
            builder.append("📖 ").append(b.getTitle())
                    .append(" - R").append(String.format("%.2f", b.getPrice()))
                    .append("\nSeller: ").append(b.getSellerName())
                    .append("\nBank: ").append(b.getBankName()) // Added bank display
                    .append("\n-----------\n");
        }
        display.setText(builder.toString());
    }
}