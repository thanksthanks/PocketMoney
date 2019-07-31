package com.example.pocketmoney.Bean;

import java.util.List;

public class MemberBean {

    public String photoPath;
    public String memName;
    public String memRegDate;
    public List<MoneyBean> moneyList;

    public void setMoneyList(List<MoneyBean> moneyBean) {
        this.moneyList = moneyBean;
    }
    
}
