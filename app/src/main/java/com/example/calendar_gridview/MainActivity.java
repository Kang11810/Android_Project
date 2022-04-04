package com.example.calendar_gridview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    GridView month_dayView;     //month_dayView 라는 달력의 날짜를 생성할 그리드뷰 객체 생성
    TextView year_monthText;    //year_monthText 라는 년, 월을 보여줄 텍스트뷰 객체 생성
    MonthAdapter adt;           //adt 라는 어댑터 객체 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        month_dayView = findViewById(R.id.monthView);   //그리드뷰 객체를 id를 참조하여 설정정
        adt = new MonthAdapter(this);            //어댑터 객체 설정
        month_dayView.setAdapter(adt);                  //그리드뷰에 어댑터 설정

        month_dayView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //그리드뷰가 클릭되었을때 실행할 이벤트
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MonthItem item = (MonthItem)adt.getItem(i);  //날짜값을 받아올 MonthItem 객체 생성
                if(item.getDay()>0){ //날짜가 존재한다면(날짜가 존재하지 않으면 날짜값이 0임)
                Toast.makeText(getApplicationContext(),String.valueOf(adt.ThisYear)+"."
                        +String.valueOf(adt.ThisMonth+1)+"."+String.valueOf(item.getDay()),
                        Toast.LENGTH_SHORT).show();
                /*adt 의 년,월 Item 의 날짜값을 토스트메세지로 출력*/}
            }
        });

        year_monthText = findViewById(R.id.monthText); //텍스트뷰 객체를 id를 참조하여 설정
        setMonthText(); //setMonthText 함수 실행

        Button Pre = findViewById(R.id.monthPrevious); //Pre 버튼객체를 id를 참조하여 생성
        Button Next = findViewById(R.id.monthNext);    //Next 버튼객체를 id를 참조하여 생성

        //이전달 버튼 이벤트 설정
        Pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adt.setPreviousMonth();     //이전달로 변경
                adt.notifyDataSetChanged(); //어댑터 데이터 갱신 후 뷰 재생성
                setMonthText();             //setMonthText 함수 실행
            }
        });

        //다음달 버튼 이벤트 설정
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adt.setNextMonth();         //다음달로 변경
                adt.notifyDataSetChanged(); //어댑터 데이터 갱신 후 뷰 재생성
                setMonthText();             //setMonthText 함수 실행
            }
        });
    }

    public void setMonthText(){
        //setMonthText 함수
        int ThisYear = adt.getThisYear();                        //ThisYear 객체를 adt 객체로부터 받아옴
        int ThisMonth = adt.getThisMonth();                      //ThisMonth 객체를 adt 객채로부터 받아옴
        year_monthText.setText(ThisYear+"년 "+(ThisMonth+1)+"월");//year_monthText 의 텍스트를 설정
    }
}