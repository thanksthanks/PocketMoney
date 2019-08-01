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


public class ExplainInsurance extends Fragment {
    public ExplainInsurance() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explain_insurance,container, false);

        ImageView insurance1 = view.findViewById(R.id.insurance1);
        ImageView insurance2 = view.findViewById(R.id.insurance2);

        insurance1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), GameMissionActivity.class);
                startActivity(i);
            }
        });

        insurance2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), GameMissionActivity.class);
                startActivity(i);
            }
        });



        return view;
    }

}
