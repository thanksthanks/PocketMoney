package com.example.pocketmoney.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketmoney.Bean.PlanBean;
import com.example.pocketmoney.Database.FileDB;
import com.example.pocketmoney.R;

public class DetailPlanActivity extends AppCompatActivity {

    TextView txtIncome,txtIncomeSource,txtSpend,txtSpendSource,txtDate,txtTotal,txtContent;
    public long planId;
    PlanBean plan;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_plan);

        findViewById(R.id.btnCancel).setOnClickListener(mBtnClick);
        findViewById(R.id.btnModifiy).setOnClickListener(mBtnClick);

        txtIncome =findViewById(R.id.txtIncome);
        txtIncomeSource =findViewById(R.id.txtIncomeSource);
        txtSpend =findViewById(R.id.txtSpend);
        txtSpendSource=findViewById(R.id.txtSpendSource);
        txtDate =findViewById(R.id.txtDate);
        txtTotal =findViewById(R.id.txtTotal);
        txtContent =findViewById(R.id.txtContent);


        Intent intent = getIntent();

        index= intent.getIntExtra("INDEX", -1); // 리스트 데이터의 Index
        if (index != -1) {
            planId=intent.getLongExtra("PLAN_ID",-1);
            plan = FileDB.findPlan(this, planId);

            txtIncome.setText(plan.income+"원");
            txtIncomeSource.setText(plan.sourceIncome);
            txtSpend.setText(plan.spend+"원");
            txtSpendSource.setText(plan.sourceSpend);
            txtTotal.setText(plan.sum);
            txtDate.setText(plan.planDate);
            txtTotal.setText(plan.sum+"원");
            txtContent.setText(plan.content);
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

                    Intent intent = new Intent(getApplicationContext(), ModifyPlanActivity.class);
                    intent.putExtra("INDEX", index);   // 원본데이터의 순번
                    intent.putExtra("PLAN_ID", plan.planId);
                    startActivityForResult(intent, 2000);
                    break;

            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2000 && resultCode == RESULT_OK) {
            finish();
        }
    }
}
