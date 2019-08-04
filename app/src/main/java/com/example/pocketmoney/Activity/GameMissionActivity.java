package com.example.pocketmoney.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.pocketmoney.Fragment.GameMission.MissionDonation1;
import com.example.pocketmoney.Fragment.GameMission.MissionDonation2;
import com.example.pocketmoney.Fragment.GameMission.MissionInsurance1;
import com.example.pocketmoney.Fragment.GameMission.MissionInsurance2;
import com.example.pocketmoney.Fragment.GameMission.MissionInvest1;
import com.example.pocketmoney.Fragment.GameMission.MissionInvest2;
import com.example.pocketmoney.Fragment.GameMission.MissionSaving1;
import com.example.pocketmoney.Fragment.GameMission.MissionSaving2;
import com.example.pocketmoney.Fragment.GameMission.MissionSpending1;
import com.example.pocketmoney.Fragment.GameMission.MissionSpending2_1;
import com.example.pocketmoney.Fragment.GameMission.MissionSpending2_2;
import com.example.pocketmoney.Fragment.GameMission.MissionSpending2_3;
import com.example.pocketmoney.R;

public class GameMissionActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    MissionInvest1 invest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mission);

        mViewPager=findViewById(R.id.viewPager);

        Intent intent = getIntent();
        int data = intent.getIntExtra("town", 0);

        if (data == 0) { //저축 town

        } else if (data == 1) { // 보험 town

        } else if (data == 2) { // 기부 town

        } else if (data == 3) { // 소비 town

        } else if (data == 4) { // 투자 town
            // 초기 세팅
            invest1 = (MissionInvest1) getSupportFragmentManager().findFragmentById(R.id.invest1);

        }

        // 돌아가기 버튼
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void InvestChange(int index) {
        if(index == 0) {
            //getSupportFragmentManager().beginTransaction().repl
        }
    }

}