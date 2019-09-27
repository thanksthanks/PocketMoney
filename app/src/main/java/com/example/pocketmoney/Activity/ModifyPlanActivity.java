package com.example.pocketmoney.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketmoney.Bean.PlanBean;
import com.example.pocketmoney.Database.FileDB;
import com.example.pocketmoney.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ModifyPlanActivity extends AppCompatActivity {

    EditText edtIncome,edtSpend,edtContent;
    TextView txtSum;
    Spinner spinnerIncome,spinnerSpend;
    int intSourceIncome,intSouceSpend;
    String mDate;
    public long planId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_plan);

        findViewById(R.id.btnSaveDetail).setOnClickListener(mBtnClick);
        findViewById(R.id.btnCancelDetail).setOnClickListener(mBtnClick);
        findViewById(R.id.btnCalclulateDetail).setOnClickListener(mBtnClick);

        edtIncome =findViewById(R.id.edtIncomeDetail);
        edtSpend =findViewById(R.id.edtSpendDetail);
        edtContent =findViewById(R.id.edtContentDetail);
        txtSum=findViewById(R.id.txtSumDetail);
        spinnerIncome=findViewById(R.id.spinnerIncome);
        spinnerSpend=findViewById(R.id.spinnerSpend);


        spinnerIncome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                intSourceIncome=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });


        spinnerSpend.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                intSouceSpend=i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        mDate=sdf.format(new Date());

        Intent intent = getIntent();

        int index = intent.getIntExtra("INDEX", -1); // 리스트 데이터의 Index
        if (index != -1) {
            planId=intent.getLongExtra("PLAN_ID",-1);
            PlanBean plan = FileDB.findPlan(this, planId);
            edtContent.setText(plan.content);
            edtIncome.setText(plan.income);
            edtSpend.setText(plan.spend);
            //txtSum.setText(plan.sum);
            spinnerIncome.setSelection(plan.intSouceIncome);
            spinnerSpend.setSelection(plan.intSouceSpend);
        }
    }


    private View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnCalclulateDetail:
                    if(TextUtils.equals(edtIncome.getText(),"") | TextUtils.equals(edtSpend.getText(),"") ) {
                        Toast.makeText(getApplicationContext(),"금액을 입력하세요",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    txtSum.setText(Integer.toString(Integer.parseInt(edtIncome.getText().toString())-
                            Integer.parseInt(edtSpend.getText().toString())));
                    break;
                case R.id.btnSaveDetail:
                    String s = txtSum.getText().toString();
                    if(s.equals("")){
                        Toast.makeText(getApplicationContext(), "계산하기 버튼을 눌러주세요!", Toast.LENGTH_LONG).show();
                        break;
                    }
                    saveProc();
                    break;
                case R.id.btnCancelDetail:
                    finish();;
                    break;

            }
        }
    };


    //저장버튼 저장처리
    private void saveProc(){

        //파일에 저장처리
        PlanBean newPlan =  FileDB.findPlan(this, planId);
        newPlan.income=edtIncome.getText().toString();
        newPlan.spend=edtSpend.getText().toString();
        newPlan.sum=txtSum.getText().toString();
        newPlan.content=edtIncome.getText().toString();
        newPlan.intSouceIncome=intSourceIncome;
        newPlan.intToSourceIncome();
        newPlan.intSouceSpend=intSouceSpend;
        newPlan.intToSourceSpend();
        newPlan.planDate=mDate;
        newPlan.content=edtContent.getText().toString();
        FileDB.setPlan(getApplicationContext(),newPlan,planId);

        setResult(RESULT_OK);
        finish();
    }
}
