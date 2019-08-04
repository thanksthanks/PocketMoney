package com.example.pocketmoney.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketmoney.Bean.PlanBean;
import com.example.pocketmoney.Database.FileDB;
import com.example.pocketmoney.R;

public class PlanActivity extends AppCompatActivity {

    EditText edtIncome,edtSpend,edtContent;
    TextView txtSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        findViewById(R.id.btnSave).setOnClickListener(mBtnClick);
        findViewById(R.id.btnCancel).setOnClickListener(mBtnClick);
        findViewById(R.id.btnCalclulate).setOnClickListener(mBtnClick);

        edtIncome =findViewById(R.id.edtIncome);
        edtSpend =findViewById(R.id.edtSpend);
        edtContent =findViewById(R.id.edtContent);
        txtSum=findViewById(R.id.txtSum);

    }

    private View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnCalclulate:
                    if(TextUtils.equals(edtIncome.getText(),"") | TextUtils.equals(edtSpend.getText(),"") ) {
                        Toast.makeText(getApplicationContext(),"금액을 입력하세요",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    txtSum.setText(Integer.toString(Integer.parseInt(edtIncome.getText().toString())-
                    Integer.parseInt(edtSpend.getText().toString())));
                    break;
                case R.id.btnSave:
                    String s = txtSum.getText().toString();
                    if(s.equals("")){
                        Toast.makeText(getApplicationContext(), "계산하기 버튼을 눌러주세요!", Toast.LENGTH_LONG).show();
                        break;
                    }
                    saveProc();
                    break;
                case R.id.btnCancel:
                    finish();;
                    break;

            }
        }
    };

    //저장버튼 저장처리
    private void saveProc(){

        //파일에 저장처리
        PlanBean newPlan = new PlanBean();
        newPlan.income=edtIncome.getText().toString();
        newPlan.spend=edtSpend.getText().toString();
        newPlan.sum=txtSum.getText().toString();
        newPlan.content=edtIncome.getText().toString();


        FileDB.addPlan(this, newPlan);

        finish();
    }
}
