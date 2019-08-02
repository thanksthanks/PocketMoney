package com.example.pocketmoney.Fragment.GameMission;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.pocketmoney.R;


public class MissionInvest1 extends Fragment {
    public MissionInvest1() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mission_invest1,container, false);

        view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("축하합니다!!");
                builder.setMessage("은행에서 투자 설명 듣기를 완료하였습니다. 열 번 듣는 것이 한 번 보는 것만 못하다는데, 투자가 무었인지 잘 들었으니 이제 직접 해볼까요?");
                builder.setNegativeButton("아니오", null);
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // TODO : MissionInvest2로 fragment 교체
                    }
                });
            }
        });
        return view;
    }
}
