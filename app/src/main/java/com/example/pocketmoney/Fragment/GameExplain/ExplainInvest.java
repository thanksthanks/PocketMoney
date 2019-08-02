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
import com.example.pocketmoney.R;


public class ExplainInvest extends Fragment {
    public ExplainInvest() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explain_invest,container, false);

        ImageView invest1 = view.findViewById(R.id.invest1);
        ImageView invest2 = view.findViewById(R.id.invest2);

        return view;
    }
}
