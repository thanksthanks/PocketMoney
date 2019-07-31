package com.example.pocketmoney.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.pocketmoney.R;
import com.google.android.material.tabs.TabLayout;

public class GamePlayActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        mTabLayout =findViewById(R.id.tabLayout);
        mViewPager=findViewById(R.id.viewPager);
    }
}
