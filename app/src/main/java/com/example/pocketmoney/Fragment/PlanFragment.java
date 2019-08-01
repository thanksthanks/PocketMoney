package com.example.pocketmoney.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pocketmoney.Activity.PlanActivity;
import com.example.pocketmoney.Bean.MemberBean;
import com.example.pocketmoney.Bean.PlanBean;
import com.example.pocketmoney.Database.FileDB;
import com.example.pocketmoney.R;

import java.util.ArrayList;
import java.util.List;


public class PlanFragment extends Fragment {

    public ListView mListPlan;
    List<PlanBean> plans = new ArrayList<>();
    ListAdapter adapter;
    MemberBean member;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan,container, false);

        mListPlan = view.findViewById(R.id.listPlan);

        view.findViewById(R.id.btnPlan).setOnClickListener(mBtnClick);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        plans = FileDB.getMemberPlanList(getContext());

        adapter = new ListAdapter(plans,getContext());
        mListPlan.setAdapter(adapter);
    }

    private View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnPlan:
                    Intent intent = new Intent(getContext(), PlanActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    class ListAdapter extends BaseAdapter {
        List<PlanBean> plans; // 원본 데이터
        Context mContext;
        LayoutInflater inflater;

        public ListAdapter(List<PlanBean> plans, Context context) {
            this.plans = plans;
            this.mContext = context;
            this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }

        public void setMemos(List<PlanBean> plans) {
            this.plans = plans;
        }

        @Override
        public int getCount() { return plans.size(); }

        @Override
        public Object getItem(int i) { return plans.get(i); }

        @Override
        public long getItemId(int i) { return i; }


        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            // view_item.xml 획득
            view = inflater.inflate(R.layout.view_plan, null);

            // 객체 획득
            TextView txtIncome = view.findViewById(R.id.txtIncome);
            TextView txtSpend = view.findViewById(R.id.txtSpend);
            TextView txtSum = view.findViewById(R.id.txtSum);
            TextView txtContent = view.findViewById(R.id.txtContent);
            TextView txtDate = view.findViewById(R.id.txtDate);

            // 원본에서 i번째 Item 획득
            final PlanBean plan = plans.get(i);

            // 원본 데이터를 UI에 적용
            txtIncome.setText(plan.income);
            txtSpend.setText(plan.spend);
            txtSum.setText(plan.sum);
            txtContent.setText(plan.content);
            txtDate.setText(plan.planDate);

            txtIncome.setTextColor(Color.parseColor("#0000FF"));
            txtSpend.setTextColor(Color.parseColor("#FF0000"));

            return view; // 완성된 UI 리턴
        }
    }


}
