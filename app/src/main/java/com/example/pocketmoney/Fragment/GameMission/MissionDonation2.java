package com.example.pocketmoney.Fragment.GameMission;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pocketmoney.Activity.GameMissionActivity;
import com.example.pocketmoney.Activity.GamePlayActivity;
import com.example.pocketmoney.R;


public class MissionDonation2 extends Fragment {
    private int coin = 20;
    public MissionDonation2() {}
    private ImageView good;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mission_donation2,container, false);
        good = view.findViewById(R.id.good);
        good.setVisibility(View.INVISIBLE);

        final TextView DonationCoin = view.findViewById(R.id.donationCoin);
        view.findViewById(R.id.plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coin++;
                if (coin >= 30) {
                    coin = 30;
                }
                DonationCoin.setText(coin + "코인");
            }
        });
        view.findViewById(R.id.minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coin--;
                if (coin <= 1) {
                    coin = 1;
                }
                DonationCoin.setText(coin + "코인");
            }
        });
        DonationCoin.setText(coin + "코인");

        view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                good.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }
}