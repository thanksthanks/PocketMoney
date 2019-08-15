package com.example.pocketmoney.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

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
import com.example.pocketmoney.Fragment.GameMission.MissionInvest3;
import com.example.pocketmoney.Fragment.GameMission.MissionSaving1;
import com.example.pocketmoney.Fragment.GameMission.MissionSaving2;
import com.example.pocketmoney.Fragment.GameMission.MissionSpending1;
import com.example.pocketmoney.Fragment.GameMission.MissionSpending2_1;
import com.example.pocketmoney.Fragment.GameMission.MissionSpending2_2;
import com.example.pocketmoney.Fragment.GameMission.MissionSpending2_3;
import com.example.pocketmoney.R;

public class GameMissionActivity extends AppCompatActivity {

    private FrameLayout missionFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    // 디폴트 fragment
    private MissionInvest1 invest1;
    private MissionDonation1 donation1;
    private MissionInsurance1 insurance1;
    private MissionSaving1 saving1;
    private MissionSpending1 spending1;

    private MissionInvest2 invest2;
    private MissionInvest3 invest3;
    private MissionDonation2 donation2;
    private MissionInsurance2 insurance2;
    private MissionSaving2 saving2;
    private MissionSpending2_1 spending2_1;
    private MissionSpending2_2 spending2_2;
    private MissionSpending2_3 spending2_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mission);

        missionFragment = findViewById(R.id.missionFragment);

        Intent intent = getIntent();
        int data = intent.getIntExtra("town", 0);

        invest1 = new MissionInvest1();
        donation1 = new MissionDonation1();
        insurance1 = new MissionInsurance1();
        saving1 = new MissionSaving1();
        spending1 = new MissionSpending1();

        invest2 = new MissionInvest2();
        invest3 = new MissionInvest3();
        donation2 = new MissionDonation2();
        insurance2 = new MissionInsurance2();
        saving2 = new MissionSaving2();
        spending2_1 = new MissionSpending2_1();
        spending2_2 = new MissionSpending2_2();
        spending2_3 = new MissionSpending2_3();

        if (data == 0) { //저축 town
            getSupportFragmentManager().beginTransaction().replace(R.id.missionFragment, saving1).commit();
        } else if (data == 1) { // 보험 town
            getSupportFragmentManager().beginTransaction().replace(R.id.missionFragment, insurance1).commit();
        } else if (data == 2) { // 기부 town
            getSupportFragmentManager().beginTransaction().replace(R.id.missionFragment, donation1).commit();
        } else if (data == 3) { // 소비 town
            getSupportFragmentManager().beginTransaction().replace(R.id.missionFragment, spending1).commit();
        } else if (data == 4) { // 투자 town
            getSupportFragmentManager().beginTransaction().replace(R.id.missionFragment, invest1).commit();
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
        if (index == 0) {
            //getSupportFragmentManager().beginTransaction().repl
        }
    }

    public void ChangeFragment(int i) {
        if (i == 0) {
            getSupportFragmentManager().beginTransaction().replace(R.id.missionFragment, invest2).commit();
        } else if (i == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.missionFragment, invest3).commit();
        } else if (i == 2) {
            getSupportFragmentManager().beginTransaction().replace(R.id.missionFragment, donation2).commit();
        } else if ( i == 3 ) {
            getSupportFragmentManager().beginTransaction().replace(R.id.missionFragment, insurance2).commit();
        } else if ( i == 4 ) {
            getSupportFragmentManager().beginTransaction().replace(R.id.missionFragment, saving2).commit();
        } else if ( i == 5 ) {
            getSupportFragmentManager().beginTransaction().replace(R.id.missionFragment, spending2_1).commit();
        } else if ( i == 6 ) {
            getSupportFragmentManager().beginTransaction().replace(R.id.missionFragment, spending2_2).commit();
        } else if ( i == 7 ) {
            getSupportFragmentManager().beginTransaction().replace(R.id.missionFragment, spending2_3).commit();
        }
    }
}