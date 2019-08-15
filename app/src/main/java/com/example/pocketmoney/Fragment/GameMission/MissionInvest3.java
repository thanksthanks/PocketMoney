package com.example.pocketmoney.Fragment.GameMission;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pocketmoney.R;


public class MissionInvest3 extends Fragment {
    private int coin = 20;
    public MissionInvest3() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mission_invest3,container, false);

        final TextView InvestCoin = view.findViewById(R.id.investCoin);
        InvestCoin.setText(coin + "코인");

        view.findViewById(R.id.plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coin++;
                InvestCoin.setText(coin + "코인");
            }
        });
        view.findViewById(R.id.minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                coin--;
                InvestCoin.setText(coin + "코인");
            }
        });
        return view;
    }
}
