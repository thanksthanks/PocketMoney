package com.example.pocketmoney.Bean;

import java.io.Serializable;

public class MoneyBean implements Serializable {
    public int money;
    public long moneyId; //고유번호
    public int type; //0이면 지출, 1이면 수입
    public String source; //돈을 어디에 썼는지 or 받았는지
    public int intSouce;
    public String moneyDate;
    public int moneyMonth;
    public int moneyYear;
    public String moneyDetail; //추가 기록

    public void intToSource(int type){
        if(type==1){
        if(this.intSouce == 0 ) {
            this.source = "용돈";
        } else if(this.intSouce == 1) {
            this.source = "아르바이트";
        } else if(this.intSouce == 2) {
            this.source ="기타";
        }
        }else if(type==0){
            if(this.intSouce == 0 ) {
                this.source = "식비";
            } else if(this.intSouce == 1) {
                this.source = "교통비";
            } else if(this.intSouce == 2) {
                this.source ="문화비";
            }else if(this.intSouce == 3) {
                this.source ="저금";
            }else if(this.intSouce == 4) {
                this.source ="기타";
            }
        }

    }



}
