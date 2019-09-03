package com.example.pocketmoney.Bean;

import java.util.ArrayList;
import java.util.List;

public class MemberBean {

    public String memName;
    public List<MoneyBean> moneyList;
    public List<PlanBean> planList;
    public ArrayList<String> wishList;

    public void setMoneyList(List<MoneyBean> moneyBean) {
        this.moneyList = moneyBean;
    }
    
}
