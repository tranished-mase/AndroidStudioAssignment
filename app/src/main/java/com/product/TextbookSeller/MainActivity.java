package com.product.TextbookSeller;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private TextbookViewModel viewModel;
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(TextbookViewModel.class);

        viewPager = findViewById(R.id.viewPager);
        bottomNav = findViewById(R.id.bottomNav);

        TextbookPagerAdapter adapter = new TextbookPagerAdapter(this, null);
        viewPager.setAdapter(adapter);

        viewPager.setOffscreenPageLimit(3);

        viewPager.setUserInputEnabled(false);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home)   { viewPager.setCurrentItem(0, false); return true; }
            if (id == R.id.nav_seller) { viewPager.setCurrentItem(1, false); return true; }
            if (id == R.id.nav_buyer)  { viewPager.setCurrentItem(2, false); return true; }
            return false;
        });
    }

    /** Called by HomeFragment CTA buttons to switch tabs. */
    public void navigateTo(int page) {
        viewPager.setCurrentItem(page, true);
        int[] ids = {R.id.nav_home, R.id.nav_seller, R.id.nav_buyer};
        bottomNav.setSelectedItemId(ids[page]);
    }
}
