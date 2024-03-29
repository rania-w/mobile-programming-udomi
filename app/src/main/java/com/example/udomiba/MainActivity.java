package com.example.udomiba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    public static Context context;


    public static final String IMAGE = "EXTRA_IMAGE";
    public static final String NAME = "EXTRA_NAME";
    public static final String DESCRIPTION = "EXTRA_DESCRIPTION";
    public static final String BIRTHDATE = "EXTRA_BIRTHDATE";
    public static final String VACCINATED = "EXTRA_VACCINATED";
    public static final String GENDER = "EXTRA_GENDER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        bottomNavigationView = findViewById(R.id.bottom_nav_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        viewPager=findViewById(R.id.fragment_container);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setUpAdapter(viewPager);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id=bundle.getInt(LoginPage.EXTRA_ID);
    }

    private void setUpAdapter(ViewPager viewPager){
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPageAdapter.addFragment(new MyPetsFragment());
        viewPageAdapter.addFragment(new HomepageFragment());
        viewPager.setAdapter(viewPageAdapter);
    }
/**/
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item){

            switch (item.getItemId()){
                case R.id.nav_my_pets:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.nav_home:
                    viewPager.setCurrentItem(1);
                    return true;

                default: return false;
            }
        }
    };
}