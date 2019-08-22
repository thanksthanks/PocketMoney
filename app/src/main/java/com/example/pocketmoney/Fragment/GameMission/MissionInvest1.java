package com.example.pocketmoney.Fragment.GameMission;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.pocketmoney.Activity.GameMissionActivity;
import com.example.pocketmoney.R;


public class MissionInvest1 extends Fragment {
    public MissionInvest1() {}
    private ImageView good;
    private TextView next;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mission_invest1,container, false);
        good = view.findViewById(R.id.good);
        next = view.findViewById(R.id.next);
        good.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);

        view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               good.setVisibility(View.VISIBLE);
               next.setVisibility(View.VISIBLE);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((GameMissionActivity)getActivity()).ChangeFragment(0);
            }
        });
        return view;
    }
}

