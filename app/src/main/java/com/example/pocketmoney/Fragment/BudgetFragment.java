package com.example.pocketmoney.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.pocketmoney.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BudgetFragment extends Fragment {

    public BudgetFragment() {}
    private FloatingActionButton fab_main;
    private Animation fab_open, fab_close;
    private boolean isFabOpen=false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_budget,container, false);

        fab_open= AnimationUtils.loadAnimation(getActivity(),R.anim.fab_open);
        fab_close=AnimationUtils.loadAnimation(getActivity(),R.anim.fab_close);

        fab_main=view.findViewById(R.id.floatingMain);

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             toggleFab();
            }
        });


        return view;
    }

    private void toggleFab() {

        if (isFabOpen) {

            fab_main.setImageResource(R.drawable.ic_add_black_24dp);

            isFabOpen = false;

        } else {

            fab_main.setImageResource(R.drawable.ic_close_black_24dp);

            isFabOpen = true;

        }

    }

}

