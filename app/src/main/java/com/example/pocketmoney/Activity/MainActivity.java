package com.example.pocketmoney.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.pocketmoney.Fragment.AssetFragment;
import com.example.pocketmoney.Fragment.BudgetFragment;
import com.example.pocketmoney.Fragment.ContentsFragment;
import com.example.pocketmoney.Fragment.RecordFragment;
import com.example.pocketmoney.Fragment.RecordParentFragment;
import com.example.pocketmoney.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private long backPressedAt;
    private BottomNavigationView mBottom;
    private AssetFragment assetFragment = new AssetFragment();
    private BudgetFragment budgetFragment = new BudgetFragment();
    private RecordParentFragment recordParentFragment = new RecordParentFragment();
    private ContentsFragment contentsFragment = new ContentsFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout =findViewById(R.id.tabLayout);
        mViewPager=findViewById(R.id.viewPager);
        mBottom = findViewById(R.id.bottomnavigation);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout, assetFragment).commitAllowingStateLoss();


        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        mBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.asset: {
                        transaction.replace(R.id.framelayout, assetFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.budget: {
                        transaction.replace(R.id.framelayout, budgetFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.record: {
                        transaction.replace(R.id.framelayout, recordParentFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.contents: {
                        transaction.replace(R.id.framelayout, contentsFragment).commitAllowingStateLoss();
                        break;
                    }
                }
                return true;
            }
        });

        /*//탭생성
        mTabLayout.addTab(mTabLayout.newTab().setText("자산"));
        mTabLayout.addTab(mTabLayout.newTab().setText("예산"));
        mTabLayout.addTab(mTabLayout.newTab().setText("기입장"));
        mTabLayout.addTab(mTabLayout.newTab().setText("콘텐츠"));
        // mTabLayout.addTab(mTabLayout.newTab().setText("설정"));
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
        });*/
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
                    return new AssetFragment();
                case 1 :
                    return new BudgetFragment();
                case 2 :
                    return new RecordParentFragment();
                case 3 :
                    return new ContentsFragment();
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
