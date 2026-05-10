package com.product.TextbookSeller;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TextbookPagerAdapter extends FragmentStateAdapter {

    private final TextbookManager manager;

    public TextbookPagerAdapter(@NonNull FragmentActivity fa, TextbookManager manager) {
        super(fa);
        this.manager = manager;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return HomeFragment.newInstance(manager);
            case 1: return SellerFragment.newInstance(manager);
            default: return BuyerFragment.newInstance(manager);
        }
    }

    @Override
    public int getItemCount() { return 3; }
}
