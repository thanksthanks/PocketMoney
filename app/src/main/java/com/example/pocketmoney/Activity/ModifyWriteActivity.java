package com.example.pocketmoney.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketmoney.Bean.MoneyBean;
import com.example.pocketmoney.Database.FileDB;
import com.example.pocketmoney.R;

public class ModifyWriteActivity extends AppCompatActivity {

    TextView txt,txtMoney,txtSpend,txtDate;
    public int intYear,intMonth;
    public long moneyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_write);

        findViewById(R.id.btnCancel).setOnClickListener(mBtnClick);
        findViewById(R.id.btnModifiy).setOnClickListener(mBtnClick);

        txt =findViewById(R.id.txt);
        txtMoney =findViewById(R.id.txtMoney);
        txtSpend =findViewById(R.id.txtSpend);
        txtDate=findViewById(R.id.txtDate);

        Intent intent = getIntent();

        int index = intent.getIntExtra("INDEX", -1); // 리스트 데이터의 Index
        if (index != -1) {
            moneyId=intent.getLongExtra("MONEY_ID",-1);
            intYear=intent.getIntExtra("YEAR",-1);
            intMonth=intent.getIntExtra("MONTH",-1);
            MoneyBean money = FileDB.findMoney(this, moneyId, intYear,intMonth);
            if (money.type==1)
                txt.setText("수입");
            else
                txt.setText("지출");
            txtMoney.setText(Integer.toString(money.money));
            txtSpend.setText(money.source);
            txtDate.setText(money.moneyDate);
        }
    }

    private View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnCancel:
                    finish();
                    break;
                case R.id.btnModifiy:
                    break;

            }
        }
    };
}
