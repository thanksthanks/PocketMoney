package com.example.pocketmoney.Fragment.GameExplain;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pocketmoney.Activity.GameMissionActivity;
import com.example.pocketmoney.Activity.GamePlayActivity;
import com.example.pocketmoney.R;


public class ExplainDonation extends Fragment {

    public ExplainDonation() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explain_donation,container, false);

        ImageView donation1 = view.findViewById(R.id.donation1);
        ImageView donation2 = view.findViewById(R.id.donation2);

        return view;
    }
}
