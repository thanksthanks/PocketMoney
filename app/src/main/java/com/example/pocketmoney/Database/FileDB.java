package com.example.pocketmoney.Database;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pocketmoney.Bean.MemberBean;
import com.example.pocketmoney.Bean.MoneyBean;
import com.example.pocketmoney.Bean.PlanBean;
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
        return member;
    }


    //용돈기록장을 추가하는 메서드
    public static void addMoney(Context context, MoneyBean moneyBean) {
        MemberBean findMember =  getMember(context);
        if(findMember==null) return;

        List<MoneyBean> moneyList = findMember.moneyList;
        if(moneyList==null){
            moneyList = new ArrayList<>();
        }
        //고유번호 생성
        moneyBean.moneyId = System.currentTimeMillis();
        moneyList.add(moneyBean);
        findMember.moneyList=moneyList;

        //저장
        setMember(context,findMember);
    }


    //용돈기록장 리스트를 획득
    public static List<MoneyBean> getMemberMoneyList(Context context) {
        MemberBean findMember =  getMember(context);
        if(findMember == null) return null;

        if(findMember.moneyList == null)
            findMember.moneyList= new ArrayList<>();

        return findMember.moneyList;
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
    public static void deleteMoney(Context context,long moneyId){
        MemberBean findMember =  getMember(context);
        List<MoneyBean> moneyList = getMemberMoneyList(context);

        for(int i=0;i<moneyList.size();i++) {
            if(moneyList.get(i).moneyId==moneyId) {
                moneyList.remove(i);
                break;
            }
        }
        findMember.moneyList = moneyList;
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
