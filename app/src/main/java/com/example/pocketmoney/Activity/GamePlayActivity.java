package com.example.pocketmoney.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.pocketmoney.Fragment.GameExplain.ExplainDonation;
import com.example.pocketmoney.Fragment.GameExplain.ExplainInsurance;
import com.example.pocketmoney.Fragment.GameExplain.ExplainInvest;
import com.example.pocketmoney.Fragment.GameExplain.ExplainSaving;
import com.example.pocketmoney.Fragment.GameExplain.ExplainSpending;
import com.example.pocketmoney.Fragment.GameExplain.ExplainState;
import com.example.pocketmoney.R;
import com.google.android.material.tabs.TabLayout;

import me.relex.circleindicator.CircleIndicator;

public class GamePlayActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        mTabLayout =findViewById(R.id.tabLayout);
        mViewPager=findViewById(R.id.viewPager);

        adapterViewPager = new mPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapterViewPager);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);
    }

    public static class mPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;
        public mPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0 :
                    return new ExplainState();
                case 1 :
                    return new ExplainInvest();
                case 2:
                    return new ExplainSaving();
                case 3:
                    return new ExplainSpending();
                case 4:
                    return new ExplainInsurance();
                case 5:
                    return new ExplainDonation();
            }
            return null;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }
    }
}
