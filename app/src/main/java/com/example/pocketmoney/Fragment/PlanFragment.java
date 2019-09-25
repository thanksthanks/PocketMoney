package com.example.pocketmoney.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pocketmoney.Activity.ModifyPlanActivity;
import com.example.pocketmoney.Activity.PlanActivity;
import com.example.pocketmoney.Bean.MemberBean;
import com.example.pocketmoney.Bean.PlanBean;
import com.example.pocketmoney.Database.FileDB;
import com.example.pocketmoney.R;

import java.util.ArrayList;
import java.util.List;


public class PlanFragment extends Fragment {

    public ListView mListPlan, mListWish;
    List<PlanBean> plans = new ArrayList<>();
    ListAdapter adapter;
    MemberBean member;

    ArrayList<String> wishes;
    ArrayAdapter<String> adapterWish;
    EditText edtWish;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plan,container, false);

        mListPlan = view.findViewById(R.id.listPlan);

        view.findViewById(R.id.btnPlan).setOnClickListener(mBtnClick);


        view.findViewById(R.id.btnAdd).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btnDel).setOnClickListener(mBtnClick);

        edtWish= view.findViewById(R.id.edtWish);

        wishes = FileDB.getMemberWishList(getContext());
        //wishes.add("Write your wishes");

        adapterWish = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_single_choice , wishes);
        mListWish = view.findViewById(R.id.listWish);
        mListWish.setAdapter(adapterWish);
        mListWish.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        plans = FileDB.getMemberPlanList(getContext());
        //wishes = FileDB.getMemberWishList(getContext());


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
                case R.id.btnAdd:
                    String edt=edtWish.getText().toString();
                    if(edt.length()!=0){
                        wishes.add(edt);
                        edtWish.setText("");
                        adapterWish.notifyDataSetChanged();
                        FileDB.setWishList(getContext(),wishes);
                        //Toast.makeText(getContext(),"입력",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btnDel:
                    int pos;
                    pos=mListWish.getCheckedItemPosition();
                    if(pos!=ListView.INVALID_POSITION){
                        wishes.remove(pos);
                        mListWish.clearChoices();
                        adapterWish.notifyDataSetChanged();
                        FileDB.setWishList(getContext(),wishes);
                    }
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
            //TextView txtContent = view.findViewById(R.id.txtContent);
            //TextView txtDate = view.findViewById(R.id.txtDate);
            Button btnDel = view.findViewById(R.id.btnDel);
            LinearLayout applyBox = view.findViewById(R.id.applybox);

            // 원본에서 i번째 Item 획득
            final PlanBean plan = plans.get(i);

            // 원본 데이터를 UI에 적용
            txtIncome.setText(plan.income);
            txtSpend.setText(plan.spend);
            txtSum.setText(plan.sum);
            //txtContent.setText(plan.content);
            //txtDate.setText(plan.planDate);
            txtIncome.setTextColor(Color.parseColor("#0000FF"));
            txtSpend.setTextColor(Color.parseColor("#FF0000"));


            applyBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ModifyPlanActivity.class);
                    intent.putExtra("INDEX", i);   // 원본데이터의 순번
                    intent.putExtra("PLAN_ID", plan.planId);
                    mContext.startActivity(intent);
                }
            });

            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
                    builder.setTitle("알림");
                    builder.setMessage("삭제하시겠습니까?");
                    builder.setPositiveButton("아니오", null);
                    builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FileDB.deletePlan(getContext(),plan.planId);
                        onResume();
                        Toast.makeText(getContext(), "삭제되었습니다", Toast.LENGTH_SHORT).show();
                    }
                });
                    builder.create().show();

            }
            });

            return view; // 완성된 UI 리턴
        }
    }


}
