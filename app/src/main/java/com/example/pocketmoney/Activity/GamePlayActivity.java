package com.example.pocketmoney.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

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

        findViewById(R.id.playInvest).setOnClickListener(mBtnClick);
        findViewById(R.id.playSaving).setOnClickListener(mBtnClick);
        findViewById(R.id.playDonation).setOnClickListener(mBtnClick);
        findViewById(R.id.playInsurance).setOnClickListener(mBtnClick);
        findViewById(R.id.playSpending).setOnClickListener(mBtnClick);

        adapterViewPager = new mPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapterViewPager);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);

    }

    private View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.playSaving:
                    Intent saving = new Intent(GamePlayActivity.this, GameMissionActivity.class);
                    saving.putExtra("town",0);
                    startActivity(saving);
                    break;
                case R.id.playInsurance:
                    Intent insurance = new Intent(GamePlayActivity.this, GameMissionActivity.class);
                    insurance.putExtra("town", 1);
                    startActivity(insurance);
                    break;
                case R.id.playDonation:
                    Intent donation = new Intent(GamePlayActivity.this, GameMissionActivity.class);
                    donation.putExtra("town", 2);
                    startActivity(donation);
                    break;
                case R.id.playSpending:
                    Intent spending = new Intent(GamePlayActivity.this, GameMissionActivity.class);
                    spending.putExtra("town", 3);
                    startActivity(spending);
                    break;
                case R.id.playInvest:
                    Intent invest = new Intent(GamePlayActivity.this, GameMissionActivity.class);
                    invest.putExtra("town", 4);
                    startActivity(invest);
                    break;
            }
        }
    };

    class mPagerAdapter extends FragmentPagerAdapter {
        int NUM_ITEMS = 6;
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
