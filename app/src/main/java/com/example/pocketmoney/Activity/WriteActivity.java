package com.example.pocketmoney.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketmoney.Bean.MoneyBean;
import com.example.pocketmoney.Database.FileDB;
import com.example.pocketmoney.R;

public class WriteActivity extends AppCompatActivity {

    EditText edtMoney;
    DatePicker datePickerIncome;
    Spinner spinnerIncome;
    String mDate,mMomth;
    int intSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        findViewById(R.id.btnCancel).setOnClickListener(mBtnClick);
        findViewById(R.id.btnSave).setOnClickListener(mBtnClick);

        edtMoney=findViewById(R.id.edtMoney);
        datePickerIncome=findViewById(R.id.datePickerIncome);
        spinnerIncome=findViewById(R.id.spinnerIncome);

        mDate = datePickerIncome.getYear() + "." + (datePickerIncome.getMonth()+1) + "." + datePickerIncome.getDayOfMonth();
        datePickerIncome.init(datePickerIncome.getYear(), datePickerIncome.getMonth(), datePickerIncome.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = year + "." + (monthOfYear+1) + "." + dayOfMonth;
                mDate=date;
                mMomth=Integer.toString(monthOfYear+1);
            }

        });

        spinnerIncome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                    if(TextUtils.equals(edtMoney.getText(),"")) {
                    Toast.makeText(getApplicationContext(),"금액을 입력하세요",Toast.LENGTH_SHORT).show();
                    break;
                }
                    saveProc();
                    break;
            }
        }
    };

    //저장버튼 저장처리
    private void saveProc(){

        int moneySum = Integer.parseInt(edtMoney.getText().toString());

        //파일에 저장처리
        MoneyBean newMoney = new MoneyBean();
        newMoney.money=moneySum;
        newMoney.type=1;
        newMoney.intSouce=intSource;
        newMoney.intToSource(1);
        newMoney.moneyDate=mDate;
        newMoney.moneyDateMonth=mMomth;

        FileDB.addMoney(this, newMoney);

        finish();
    }
}
