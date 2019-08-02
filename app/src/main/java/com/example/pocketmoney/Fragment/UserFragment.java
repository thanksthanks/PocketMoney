package com.example.pocketmoney.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.pocketmoney.Bean.MemberBean;
import com.example.pocketmoney.Database.FileDB;
import com.example.pocketmoney.R;

public class UserFragment extends Fragment {
    TextView txtName;
    MemberBean mMember;
    String name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user,container, false);

        txtName=view.findViewById(R.id.txtName);

        view.findViewById(R.id.btnName).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btnChange).setOnClickListener(mBtnClick);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MemberBean member= FileDB.getMember(getContext());
        txtName.setText(member.memName);

    }


    private View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnName:
                    AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
                    ad.setTitle("이름 수정하기");       // 제목 설정
                    ad.setMessage("이름을 입력하세요");   // 내용 설정

                    final EditText et = new EditText(getContext());
                    et.setInputType(InputType.TYPE_CLASS_TEXT);
                    ad.setView(et);

                    mMember = FileDB.getMember(getContext());
                    et.setText(mMember.memName);

                    // 확인 버튼 설정
                    ad.setPositiveButton("수정하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            name = et.getText().toString();
                            if(TextUtils.equals(name,"")) {
                                Toast.makeText(getContext(), "이름을 입력하지 않았습니다", Toast.LENGTH_LONG).show();
                            } else{
                                mMember.memName=name;
                                FileDB.setMember(getContext(),mMember);
                                dialog.dismiss();
                                onResume();
                                Toast.makeText(getContext(), "이름이 변경되었습니다", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            onResume();
                        }
                    });
                    ad.show();
                    break;
                case R.id.btnChange:

                    break;
            }
        }
    };

}
