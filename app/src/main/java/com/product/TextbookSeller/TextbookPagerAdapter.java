package com.product.TextbookSeller;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TextbookPagerAdapter extends FragmentStateAdapter {

    public TextbookPagerAdapter(@NonNull FragmentActivity fa, TextbookManager manager) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return HomeFragment.newInstance(null);
            case 1: return SellerFragment.newInstance(null);
            default: return BuyerFragment.newInstance(null);
        }
    }

    @Override
    public int getItemCount() { return 3; }
}
