package com.example.pocketmoney.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pocketmoney.Activity.RecordActivity;
import com.example.pocketmoney.R;


public class RecordFragment extends Fragment {

    public RecordFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record,container, false);

        view.findViewById(R.id.btnRecord).setOnClickListener(mBtnClick);

        return view;
    }

    private View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnRecord:
                    Intent intent = new Intent(getContext(), RecordActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

}
