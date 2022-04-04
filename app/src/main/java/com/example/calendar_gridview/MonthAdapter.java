package com.example.calendar_gridview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import java.util.Calendar;


public class MonthAdapter extends BaseAdapter {
    Calendar cal; //java 의 캘린더 함수

    Context mContext;

    int ThisYear;       //연
    int ThisMonth;      //월
    MonthItem[] items;  //일

    MonthAdapter(Context context){
        super();
        mContext = context;
        init();
    }

    MonthAdapter(Context context, AttributeSet attrs){
        super();
        mContext = context;
        init();
    }
    /*어댑터 클래스 생성(MonthItemView 를 레이아웃에 붙이기 위한 클래스)*/

    public void calculate(){
        for(int i=0; i<items.length; i++){ //items[] 모든 값 0으로 초기화
            items[i] = new MonthItem(0);
        }

        cal.set(Calendar.DAY_OF_MONTH, 1); //1일로 설정

        int startDay = cal.get(Calendar.DAY_OF_WEEK); //현재 달 1일의 요일 (1~7=일~토)
        int lastDay = cal.getActualMaximum(Calendar.DATE); //달의 마지막 날짜

        int cnt = 1;
        for(int i=startDay-1; i<startDay-1+lastDay; i++){
            /* 1일의 요일에 따라 시작위치 다르고 마지막 날짜까지 값 지정*/
            items[i] = new MonthItem(cnt);
            cnt++;
        }

        ThisYear = cal.get(Calendar.YEAR);   //현재연도값
        ThisMonth = cal.get(Calendar.MONTH); //현재월값
    }

    public void init(){
        //초기화(재설정)
        cal = Calendar.getInstance();   //Calendar 객체 가져오기
        items = new MonthItem[7*6];     //아이템 크기 결정(가로7, 세로6)
        calculate();                    //날짜 계산해서 items[] 배열 값 설정
    }

    public void setPreviousMonth(){     //한 달 앞으로 가고 다시 계산
        cal.add(Calendar.MONTH, -1);
        calculate();
    }

    public void setNextMonth(){
        cal.add(Calendar.MONTH, 1);  //한 달 뒤로가고 다시 계산
        calculate();
    }

    @Override
    public int getCount() {
        return items.length; //아이템의 길이를 리턴하는 함수
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MonthItemView view = new MonthItemView(mContext); //MonthItemView 객체 생성
        MonthItem item = items[position];
        view.setItem(item);  //날짜값이 0이라면 ""을, 아니면 각 날짜를 View 의 Text 에 입력

        if(position%7==0){   //일요일은 날짜 색 빨간색으로
            view.setTextColor(Color.RED);
        }
        if(position%7==6){   //토요일은 날짜 색 파란색으로
            view.setTextColor(Color.BLUE);
        }

        GridView.LayoutParams params = new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT,150);
        /*그리드뷰의 레이아웃 파라미터 생성(가로 MATCH_PARENT, 세로 150*/
        view.setLayoutParams(params); //그리드뷰의 레이아웃 파라미터 설정

        return view; //뷰 리턴
    }

    @Override
    public Object getItem(int position) {
        return items[position]; //그리드뷰 객체의 날짜에 해당하는 item 리턴
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public int getThisYear(){
        return ThisYear; //현재연도 리턴
    }

    public int getThisMonth(){
        return ThisMonth; //현재월 리턴
    }

}
