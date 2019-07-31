package com.example.pocketmoney.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pocketmoney.Activity.LoginActivity;
import com.example.pocketmoney.Activity.MainActivity;
import com.example.pocketmoney.Activity.WriteActivity;
import com.example.pocketmoney.Bean.MemberBean;
import com.example.pocketmoney.Bean.MoneyBean;
import com.example.pocketmoney.Database.FileDB;
import com.example.pocketmoney.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class WriteFragment extends Fragment {

    public static final int SAVE = 1001;

    public ListView mListMoney;
    List<MoneyBean> moneys = new ArrayList<>();
    ListAdapter adapter;
    MemberBean member;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write,container, false);

        view.findViewById(R.id.btnIncome).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btnSpend).setOnClickListener(mBtnClick);

        mListMoney = view.findViewById(R.id.listMoney);
        member= FileDB.getMember(getContext());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        moneys = FileDB.getMemberMoneyList(getContext());

        adapter = new ListAdapter(moneys,getContext());
        mListMoney.setAdapter(adapter);
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

    class ListAdapter extends BaseAdapter {
        List<MoneyBean> moneys; // 원본 데이터
        Context mContext;
        LayoutInflater inflater;

        public ListAdapter(List<MoneyBean> moneys, Context context) {
            this.moneys = moneys;
            this.mContext = context;
            this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }

        public void setMemos(List<MoneyBean> moneys) {
            this.moneys = moneys;
        }

        @Override
        public int getCount() { return moneys.size(); }

        @Override
        public Object getItem(int i) { return moneys.get(i); }

        @Override
        public long getItemId(int i) { return i; }


        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            // view_item.xml 획득
            view = inflater.inflate(R.layout.view_money, null);

            // 객체 획득
            TextView txtDate = view.findViewById(R.id.txtDate);
            TextView txtSource = view.findViewById(R.id.txtSource);
            TextView txtMoney = view.findViewById(R.id.txtMoney);

            // 원본에서 i번째 Item 획득
            final MoneyBean money = moneys.get(i);

            // 원본 데이터를 UI에 적용
            txtDate.setText(money.moneyDate);
            txtSource.setText(money.source);
            txtMoney.setText(money.money);


/*            Typeface typeface = Typeface.createFromAsset(getResources().getAssets(),"godo.ttf");
            txtMoney.setTypeface(typeface);
            txtDate.setTypeface(typeface);*/

            return view; // 완성된 UI 리턴
        }
    }
}
