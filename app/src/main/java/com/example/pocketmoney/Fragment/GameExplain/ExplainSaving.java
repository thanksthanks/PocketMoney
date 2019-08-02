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


public class ExplainSaving extends Fragment {
    public ExplainSaving() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explain_saving,container, false);

        ImageView saving1 = view.findViewById(R.id.saving1);
        ImageView saving2 = view.findViewById(R.id.saving2);

        return view;
    }
}
