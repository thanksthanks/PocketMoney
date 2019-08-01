package com.example.pocketmoney.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pocketmoney.Activity.GamePlayActivity;
import com.example.pocketmoney.R;


public class GameFragment extends Fragment {

    public GameFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game,container, false);

        Button expalin = view.findViewById(R.id.Expain);
        Button start = view.findViewById(R.id.Start);

        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), GamePlayActivity.class);
                // startActivity(i);
                startActivityForResult(i,0);
            }
        });
        return view;
    }

}
