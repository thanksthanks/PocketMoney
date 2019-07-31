package com.example.pocketmoney.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocketmoney.R;

public class PlanActivity extends AppCompatActivity {

    EditText edtIncome,edtSpend;
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
        txtSum=findViewById(R.id.txtSum);

    }

    private View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnCalclulate:
                    txtSum.setText(Integer.toString(Integer.parseInt(edtIncome.getText().toString())-
                    Integer.parseInt(edtSpend.getText().toString())));
                    break;
                case R.id.btnSave:

                    break;
                case R.id.btnCancel:
                    finish();;
                    break;

            }
        }
    };
}
