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

import androidx.lifecycle.ViewModelProvider;

public class HomeFragment extends Fragment {

    private TextbookViewModel viewModel;

    public static HomeFragment newInstance(TextbookManager manager) {
        return new HomeFragment();
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

        viewModel = new ViewModelProvider(requireActivity()).get(TextbookViewModel.class);

        TextView tvTotalBooks = view.findViewById(R.id.tvTotalBooks);
        TextView tvTotalSold  = view.findViewById(R.id.tvTotalSold);
        LinearLayout ctaSell  = view.findViewById(R.id.ctaSell);
        LinearLayout ctaBuy   = view.findViewById(R.id.ctaBuy);

        viewModel.getInventory().observe(getViewLifecycleOwner(), books -> {
            tvTotalBooks.setText(String.valueOf(viewModel.getTotalBooks()));
            tvTotalSold.setText(String.valueOf(viewModel.getSoldCount()));
        });

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

}
