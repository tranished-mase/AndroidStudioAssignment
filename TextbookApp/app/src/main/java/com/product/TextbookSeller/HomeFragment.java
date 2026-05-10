package com.product.TextbookSeller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private TextbookManager manager;

    public static HomeFragment newInstance(TextbookManager manager) {
        HomeFragment f = new HomeFragment();
        f.manager = manager;
        return f;
    }

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvTotalBooks = view.findViewById(R.id.tvTotalBooks);
        TextView tvTotalSold  = view.findViewById(R.id.tvTotalSold);
        LinearLayout ctaSell  = view.findViewById(R.id.ctaSell);
        LinearLayout ctaBuy   = view.findViewById(R.id.ctaBuy);

        updateStats(tvTotalBooks, tvTotalSold);

        // Navigate to Seller tab
        ctaSell.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navigateTo(1);
            }
        });

        // Navigate to Buyer tab
        ctaBuy.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navigateTo(2);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh stats every time home is shown
        View v = getView();
        if (v != null) {
            updateStats(v.findViewById(R.id.tvTotalBooks),
                        v.findViewById(R.id.tvTotalSold));
        }
    }

    private void updateStats(TextView tvBooks, TextView tvSold) {
        int total = manager.getAllBooks().size();
        int sold  = manager.getSoldCount();
        tvBooks.setText(String.valueOf(total));
        tvSold.setText(String.valueOf(sold));
    }
}
