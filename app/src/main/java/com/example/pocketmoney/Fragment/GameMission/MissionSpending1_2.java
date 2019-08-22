package com.example.pocketmoney.Fragment.GameMission;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pocketmoney.R;


public class MissionSpending1_2 extends Fragment {
    public MissionSpending1_2() {}
    private ImageView good;
    private Button next;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mission_spending1_2,container, false);

        good = view.findViewById(R.id.good);
        next = view.findViewById(R.id.next);

        good.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);

        view.findViewById(R.id.yes).setOnClickListener(mBtnClick);
        view.findViewById(R.id.no).setOnClickListener(mBtnClick);

        return view;
    }

    private View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.yes:
                    good.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                    break;
                case R.id.no:
                    getActivity().finish();
                    break;
            }
        }
    };
}
