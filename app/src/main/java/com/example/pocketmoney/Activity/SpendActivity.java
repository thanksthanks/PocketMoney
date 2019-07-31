package com.example.pocketmoney.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketmoney.Bean.MemberBean;
import com.example.pocketmoney.Bean.MoneyBean;
import com.example.pocketmoney.Database.FileDB;
import com.example.pocketmoney.R;

public class SpendActivity extends AppCompatActivity {

    EditText edtMoney;
    DatePicker datePickerSpend;
    Spinner spinnerSpend;
    String mDate;
    int intSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spend);

        findViewById(R.id.btnCancel).setOnClickListener(mBtnClick);
        findViewById(R.id.btnSave).setOnClickListener(mBtnClick);

        edtMoney=findViewById(R.id.edtMoney);
        datePickerSpend=findViewById(R.id.datePickerSpend);
        spinnerSpend=findViewById(R.id.spinnerSpend);

        mDate = datePickerSpend.getYear() + "." + (datePickerSpend.getMonth()+1) + "." + datePickerSpend.getDayOfMonth();
        datePickerSpend.init(datePickerSpend.getYear(), datePickerSpend.getMonth(), datePickerSpend.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = year + "." + (monthOfYear+1) + "." + dayOfMonth;
                mDate=date;
            }

        });

        spinnerSpend.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                intSource=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
    }

    private View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnCancel:
                    finish();
                    break;
                case R.id.btnSave:
                    saveProc();
                    break;

            }
        }
    };

    //저장버튼 저장처리
    private void saveProc(){

        String moneySum = edtMoney.getText().toString();

        //파일에 저장처리
        MoneyBean newMoney = new MoneyBean();
        newMoney.money=moneySum;
        newMoney.type=0;
        newMoney.intSouce=intSource;
        newMoney.intToSource(0);
        newMoney.moneyDate=mDate;

        MemberBean member = FileDB.getMember(this);
        FileDB.addMoney(this, newMoney);

        finish();
    }


}
