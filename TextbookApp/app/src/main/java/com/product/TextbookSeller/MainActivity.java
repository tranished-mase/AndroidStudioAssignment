package com.product.TextbookSeller;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // One shared manager — all fragments read and write this same instance
    private final TextbookManager manager = new TextbookManager();
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        bottomNav = findViewById(R.id.bottomNav);

        TextbookPagerAdapter adapter = new TextbookPagerAdapter(this, manager);
        viewPager.setAdapter(adapter);

        // Keep all 3 fragments alive so inventory survives navigation
        viewPager.setOffscreenPageLimit(3);

        // Disable swiping — use bottom nav only
        viewPager.setUserInputEnabled(false);

        // Bottom nav → ViewPager
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
