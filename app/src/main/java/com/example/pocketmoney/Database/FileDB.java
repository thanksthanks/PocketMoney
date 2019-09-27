package com.example.pocketmoney.Database;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.pocketmoney.Bean.MemberBean;
import com.example.pocketmoney.Bean.MoneyBean;
import com.example.pocketmoney.Bean.PlanBean;
import com.example.pocketmoney.Bean.YearListBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class FileDB {

    private  static final String FILE_DB="FileDB";
    private  static Gson mGson = new Gson();

    private static SharedPreferences getSP(Context context){
        SharedPreferences sp = context.getSharedPreferences(FILE_DB,Context.MODE_PRIVATE);
        return sp;
    }

    public static MemberBean getMember(Context context) {
        String listStr = getSP(context).getString("member",null);
        //저장된 리스트가 없을 경우에 새로운 리스트를 리턴한다
        if(listStr == null){
            return new MemberBean();
        }
        //Gson으로 변환한다
        MemberBean member = mGson.fromJson(listStr,new TypeToken<MemberBean>(){}.getType() );
        //MemberBean member = mGson.fromJson(listStr,MemberBean[].class );
        //MemberBean member = mGson.fromJson(listStr, MemberBean.class);
        return member;
    }



    //월별로 찾기
    public static YearListBean findList(Context context,int year, int month){
        long i=0;
        MemberBean findMember =  getMember(context);
        List<YearListBean> moneyList = findMember.moneyList;

        YearListBean y=null;
        String s = year+"."+month;

        for(YearListBean yearListBean : moneyList){
            if(TextUtils.equals(yearListBean.yearMonth, s) ){
                y = yearListBean;
                break;
            }
        }
        return y;
    }


    //용돈기록장을 추가하는 메서드
    public static void addMoney(Context context, MoneyBean moneyBean) {
        MemberBean findMember =  getMember(context);
        if(findMember==null) return;
        int exist=0;
        //고유번호 생성
        moneyBean.moneyId = System.currentTimeMillis();

        List<YearListBean> moneyList = findMember.moneyList;

        if(moneyList==null){
            moneyList = new ArrayList<>();
            YearListBean yearList = new YearListBean();
            yearList.moneyList=new ArrayList<>();
            yearList.year=moneyBean.moneyYear;
            yearList.month=moneyBean.moneyMonth;
            yearList.moneyList.add(moneyBean);
            //고유번호 생성
            yearList.yearMonth = yearList.year+"."+yearList.month;
            moneyList.add(yearList);
            exist=1;
            }
        else {

            for(YearListBean yearListBean : moneyList){
                if(yearListBean.year == moneyBean.moneyYear && yearListBean.month==moneyBean.moneyYear){
                    if(yearListBean.moneyList==null)
                        yearListBean.moneyList=new ArrayList<>();
                    yearListBean.moneyList.add(moneyBean);
                    moneyList.add(yearListBean);
                    exist=1;//이미 해당 월의 리스트가 존재해서 집어넣음
                    break;
                }
            }
            if(exist==0){
                YearListBean yearList = new YearListBean();
                yearList.moneyList = new ArrayList<>();
                yearList.year=moneyBean.moneyYear;
                yearList.month=moneyBean.moneyMonth;
                yearList.yearMonth = yearList.year+"."+yearList.month;
                yearList.moneyList.add(moneyBean);
                moneyList.add(yearList);
            }
        }

        findMember.moneyList=moneyList;


        //저장
        setMember(context,findMember);
    }



    //용돈기록장 리스트를 획득
    public static List<MoneyBean> getMemberMoneyList(Context context, int year, int month) {
        MemberBean findMember =  getMember(context);
        if(findMember == null) return null;

        YearListBean yearListBean = findList(context,year, month);

        if(yearListBean== null) {
            yearListBean = new YearListBean();
            yearListBean.moneyList = new ArrayList<>();
        }

        return yearListBean.moneyList;
    }

    //특정 용돈기록장 찾기
    public static MoneyBean findMoney(Context context, long moneyId, int year, int month){
        List<MoneyBean> moneyList = getMemberMoneyList(context,year,month);
        MoneyBean m=null;
        for(MoneyBean money : moneyList){
            if(money.moneyId == moneyId){
                m = money;
                break;
            }
        }
        return m;
    }

    //용돈계획서를 추가하는 메서드
    public static void addPlan(Context context, PlanBean planBean) {
        MemberBean findMember =  getMember(context);
        if(findMember==null) return;

        List<PlanBean> planList = findMember.planList;
        if(planList==null){
            planList = new ArrayList<>();
        }
        //고유번호 생성
        planBean.planId= System.currentTimeMillis();
        planList.add(planBean);
        findMember.planList=planList;

        //저장
        setMember(context,findMember);
    }

    //용돈계획서 리스트를 획득
    public static List<PlanBean> getMemberPlanList(Context context) {
        MemberBean findMember =  getMember(context);
        if(findMember == null) return null;

        if(findMember.planList == null)
            findMember.planList= new ArrayList<>();

        return findMember.planList;
    }

    //특정 용돈계획서 찾기
    public static PlanBean findPlan(Context context, long planId){
        List<PlanBean> planList = getMemberPlanList(context);
        PlanBean p=null;
        for(PlanBean plan : planList){
            if(plan.planId == planId){
                p = plan;
                break;
            }
        }
        return p;
    }

    //기존 용돈계획서 수정
    public  static void setPlan(Context context, PlanBean planBean, long planId){
        MemberBean findMember =  getMember(context);
        List<PlanBean> planList = getMemberPlanList(context);
        PlanBean p = findPlan(context,planId);

        p.spend=planBean.spend;
        p.income=planBean.income;
        //p.planDate=planBean.planDate;
        p.sum=planBean.sum;
        p.content=planBean.content;
        p.planId=planId;

        for(int i=0;i<planList.size();i++) {
            if(planList.get(i).planId==planId) {
                planList.set(i,p);
                break;
            }
        }

        findMember.planList = planList;
        setMember(context,findMember);
    }

    //위시리스트를 추가하는 메서드
    public static void setWishList(Context context, ArrayList<String> wishes) {
        MemberBean findMember =  getMember(context);
        if(findMember==null) return;

        findMember.wishList=wishes;

        //저장
        setMember(context,findMember);
    }
    //위시리스트  획득
    public static ArrayList<String> getMemberWishList(Context context) {
        MemberBean findMember =  getMember(context);
        if(findMember == null) return null;

        if(findMember.wishList == null){
            findMember.wishList= new ArrayList<>();
            findMember.wishList.add("Write your wishes");
        }

        return findMember.wishList;
    }


    //멤버 교체. 기록 수정했을때나
    public static void setMember(Context context, MemberBean memberBean){
        //새롭게 update 된 리스트를를 저장한다
        String jsonStr = mGson.toJson(memberBean);
        //멤버 리스트를 저장한다
        SharedPreferences.Editor editor = getSP(context).edit();
        editor.putString("member",jsonStr);
        editor.commit();
    }

    //기록 삭제
    public static void deleteMoney(Context context,long moneyId,int year, int month){
        MemberBean findMember =  getMember(context);
        List<MoneyBean> moneyList = getMemberMoneyList(context,year, month);

        for(int i=0;i<moneyList.size();i++) {
            if(moneyList.get(i).moneyId==moneyId) {
                moneyList.remove(i);
                break;
            }
        }

        String s = year+"."+month;

        for(int i = 0; i < findMember.moneyList.size();i++){
            if(TextUtils.equals(findMember.moneyList.get(i).yearMonth,s)){
                findMember.moneyList.get(i).moneyList = moneyList;
                break;
            }
        }

        //findMember.moneyList = moneyList;

        setMember(context,findMember);
    }

    //계획 삭제
    public static void deletePlan(Context context,long planId){
        MemberBean findMember =  getMember(context);
        List<PlanBean> planList = findMember.planList;

        for(int i=0;i<planList.size();i++) {
            if(planList.get(i).planId==planId) {
                planList.remove(i);
                break;
            }
        }

        findMember.planList = planList;
        setMember(context,findMember);
    }
}
