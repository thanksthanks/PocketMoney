package com.example.pocketmoney.Bean;

import java.io.Serializable;

public class PlanBean implements Serializable {
    public long planId;
    public String income;
    public String spend;
    public String sum;
    public String planDate;
    public String content;

    public String sourceIncome; //돈을 어디에서 받았는지
    public int intSouceIncome;
    public String sourceSpend; //돈을 어디에 썼는지
    public int intSouceSpend;



    public void intToSourceIncome(){
            if(this.intSouceIncome == 0 ) {
                this.sourceIncome = "용돈";
            } else if(this.intSouceIncome == 1) {
                this.sourceIncome = "아르바이트";
            } else if(this.intSouceIncome == 2) {
                this.sourceIncome ="기타";
            }

    }

    public void intToSourceSpend(){
        if(this.intSouceSpend == 0 ) {
                        this.sourceSpend = "식비";
        } else if(this.intSouceSpend == 1) {
                        this.sourceSpend = "교통비";
        } else if(this.intSouceSpend == 2) {
                        this.sourceSpend ="문화비";
        }else if(this.intSouceSpend == 3) {
                        this.sourceSpend ="저금";
        }else if(this.intSouceSpend == 4) {
                        this.sourceSpend ="기타";
                    }
    }

}
