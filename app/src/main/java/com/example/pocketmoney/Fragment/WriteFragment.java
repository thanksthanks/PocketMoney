package com.example.pocketmoney.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pocketmoney.Activity.ModifyWriteActivity;
import com.example.pocketmoney.Activity.SpendActivity;
import com.example.pocketmoney.Activity.WriteActivity;
import com.example.pocketmoney.Bean.MemberBean;
import com.example.pocketmoney.Bean.MoneyBean;
import com.example.pocketmoney.Database.FileDB;
import com.example.pocketmoney.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class WriteFragment extends Fragment {

    public static final int SAVE = 1001;

    public ListView mListMoney;
    List<MoneyBean> moneys = new ArrayList<>();
    ListAdapter adapter;
    MemberBean member;

    Spinner yearSpinner,monthSpinner;
    int intYear,intMonth;
    String yearMonth;
    TextView txtIncome,txtSpend,txtSum;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write, container, false);

        view.findViewById(R.id.btnIncome).setOnClickListener(mBtnClick);
        view.findViewById(R.id.btnSpend).setOnClickListener(mBtnClick);

        txtIncome = view.findViewById(R.id.txtIncome);
        txtSpend = view.findViewById(R.id.txtSpend);
        txtSum = view.findViewById(R.id.txtSum);

        mListMoney = view.findViewById(R.id.listMoney);

        yearSpinner = view.findViewById(R.id.yearSpinner);
        monthSpinner=view.findViewById(R.id.monthSpinner);


/*        NumberPicker picker1 = view.findViewById(R.id.picker1);
        picker1.setMinValue(1);
        picker1.setMaxValue(12);
        picker1.setWrapSelectorWheel(false);
        intMonth=picker1.getValue();*/

        //picker1.setDisplayedValues(new String[]{"1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"});


        Calendar cal = Calendar.getInstance();
        //int year = cal.get ( cal.YEAR );
        int month = cal.get(cal.MONTH);
        yearSpinner.setSelection(2);
        monthSpinner.setSelection(month);
        //picker1.setValue(month);

        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                intYear=i;
                onResume();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                intMonth=i;
                onResume();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

    return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        intYear= Integer.parseInt(yearSpinner.getSelectedItem().toString());
        intMonth= Integer.parseInt(monthSpinner.getSelectedItem().toString());
        yearMonth = intYear+"."+intMonth;
        Toast.makeText(getContext(),"year: "+intYear+"month: "+intMonth,Toast.LENGTH_LONG).show();

        int sumIncome=0,sumSpend=0,sum=0;
        member = FileDB.getMember(getContext());
        moneys = FileDB.getMemberMoneyList(getContext(),intYear,intMonth);

        adapter = new ListAdapter(moneys,getContext());
        mListMoney.setAdapter(adapter);

        for(int i=0; i<moneys.size(); i++){
            if(moneys.get(i).type==0)
                sumSpend=sumSpend+moneys.get(i).money;
            else if (moneys.get(i).type==1)
                sumIncome=sumIncome+moneys.get(i).money;
        }
        txtIncome.setText(Integer.toString(sumIncome));
        txtSpend.setText(Integer.toString(sumSpend));
        sum=sumIncome-sumSpend;
        txtSum.setText(Integer.toString(sum));
    }


    private View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnIncome:
                    Intent intent = new Intent(getContext(), WriteActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btnSpend:
                    Intent intent2 = new Intent(getContext(), SpendActivity.class);
                    startActivity(intent2);
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
            Button btnDel = view.findViewById(R.id.btnDel);
            LinearLayout applyBox = view.findViewById(R.id.applybox);

            // 원본에서 i번째 Item 획득
            final MoneyBean money = moneys.get(i);

            // 원본 데이터를 UI에 적용
            txtDate.setText(money.moneyDate);
            txtSource.setText(money.source);
            txtMoney.setText(Integer.toString(money.money));

            //txtMoney.setBackgroundColor(Color.parseColor("#0000FF"));
            if (money.type == 0) {
                txtMoney.setTextColor(Color.parseColor("#0000FF"));
            } else if (money.type == 1) {
                txtMoney.setTextColor(Color.parseColor("#FF0000"));
            }

            applyBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ModifyWriteActivity.class);
                    intent.putExtra("INDEX", i);   // 원본데이터의 순번
                    intent.putExtra("MONEY_ID", money.moneyId);
                    intent.putExtra("YEAR",intYear);
                    intent.putExtra("MONTH",intMonth);
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
                            FileDB.deleteMoney(getContext(), money.moneyId, intYear,intMonth);
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
