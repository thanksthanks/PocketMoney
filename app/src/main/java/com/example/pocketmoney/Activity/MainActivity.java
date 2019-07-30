package com.example.pocketmoney.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.pocketmoney.Fragment.GameFragment;
import com.example.pocketmoney.Fragment.HomeFragment;
import com.example.pocketmoney.Fragment.PlanFragment;
import com.example.pocketmoney.Fragment.UserFragment;
import com.example.pocketmoney.Fragment.WriteFragment;
import com.example.pocketmoney.R;
import com.google.android.material.tabs.TabLayout;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private long backPressedAt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout =findViewById(R.id.tabLayout);
        mViewPager=findViewById(R.id.viewPager);

        //탭생성
        mTabLayout.addTab(mTabLayout.newTab().setText("메인화면"));
        mTabLayout.addTab(mTabLayout.newTab().setText("용돈 기록장"));
        mTabLayout.addTab(mTabLayout.newTab().setText("용돈 기록하기"));
        mTabLayout.addTab(mTabLayout.newTab().setText("게임하기"));
        mTabLayout.addTab(mTabLayout.newTab().setText("회원정보"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //viewPager 생성
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),mTabLayout.getTabCount());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }//onCreat 종료

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private int tabCount;

        public ViewPagerAdapter(FragmentManager fm, int count){
            super(fm);
            this.tabCount = count;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new HomeFragment();
                case 1 :
                    return new PlanFragment();
                case 2 :
                    return new WriteFragment();
                case 3 :
                    return new GameFragment();
                case 4 :
                    return new UserFragment();

            }
            return null;
        }

        @Override
        public int getCount() {
            return tabCount;
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressedAt + TimeUnit.SECONDS.toMillis(2) > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        }
        else {
            if(this instanceof MainActivity) {
                Toast.makeText(this, "한번 더 뒤로가기 클릭시 앱을 종료 합니다.", Toast.LENGTH_LONG).show();
                backPressedAt = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        }
    }

}
