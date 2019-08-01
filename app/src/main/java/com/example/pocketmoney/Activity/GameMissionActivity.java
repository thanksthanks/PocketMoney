package com.example.pocketmoney.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pocketmoney.R;
import com.google.android.material.tabs.TabLayout;

public class GameMissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mission);


        // 돌아가기 버튼
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new
                                        View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
