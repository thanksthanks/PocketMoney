package com.example.pocketmoney.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pocketmoney.Activity.LoginActivity;
import com.example.pocketmoney.Activity.MainActivity;
import com.example.pocketmoney.Activity.WriteActivity;
import com.example.pocketmoney.R;


public class WriteFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write,container, false);

        view.findViewById(R.id.btnIncome).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btnSpend).setOnClickListener(mBtnClick);

        return view;
    }


    private View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnIncome:
                    Intent i = new Intent(getContext(), WriteActivity.class);
                    startActivity(i);
                    break;
                case R.id.btnSpend:
                    break;

            }
        }
    };
}
