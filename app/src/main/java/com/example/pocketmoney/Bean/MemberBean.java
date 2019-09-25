package com.example.pocketmoney.Bean;

import java.util.ArrayList;
import java.util.List;

public class MemberBean {

    public String memName;

    /*public List<MoneyBean> moneyList;*/

    public List<PlanBean> planList;

    public List<MoneyBean> moneyList;

    //public YearListBean yearList[] = new YearListBean[4];//yearList[0]은 2019년 yearList[3]=2022년

    //public List<List<List<MoneyBean>>> moneyList; // 2019년 부터 2022년까지만

   //public List<YearListBean> moneyList;



    public List<YearListBean> yearList2019;
    public List<YearListBean> yearList2020;
    public List<YearListBean> yearList2021;
    public List<YearListBean> yearList2022;


    public ArrayList<String> wishList;

  /*  public void setMoneyList(List<MoneyBean> moneyBean) {
        this.moneyList = moneyBean;
    }*/
    
}
