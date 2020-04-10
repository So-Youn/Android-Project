package exam.day03.view.selectview.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import exam.day03.view.selectview.R;
import exam.day03.view.selectview.view.adapter.ActorItem;
import exam.day03.view.selectview.view.adapter.ExamAdapter;


public class SelectView_ExamActity extends AppCompatActivity {
    ListView listView;
    ActorItem actorItem;
    ArrayList<ActorItem> actorlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_exam_main);
        listView = findViewById(R.id.cust_listview);

        actorlist = new ArrayList<ActorItem>();
        actorItem = new ActorItem(R.drawable.ansoccer,"안정환","2020/04/06","멋져");
        actorlist.add(actorItem);
        actorItem = new ActorItem(R.drawable.chasoccer,"차범근","2020/04/06","아들~~");
        actorlist.add(actorItem);
        actorItem = new ActorItem(R.drawable.jjangkim,"김어준","2020/04/06","찐");
        actorlist.add(actorItem);
        actorItem = new ActorItem(R.drawable.kbr,"김범룡","2020/04/06","진짜가수");
        actorlist.add(actorItem);
        actorItem = new ActorItem(R.drawable.kimdong,"이민호","2020/04/06","멋져");
        actorlist.add(actorItem);
        actorItem = new ActorItem(R.drawable.leemin,"이민호","2020/04/06","멋져");
        actorlist.add(actorItem);
        actorItem = new ActorItem(R.drawable.parksoccer,"박지성","2020/04/06","멋져");
        actorlist.add(actorItem);
        actorItem = new ActorItem(R.drawable.ansoccer,"안정환","2020/04/06","멋져");
        actorlist.add(actorItem);
        actorItem = new ActorItem(R.drawable.chasoccer,"차범근","2020/04/06","아들~~");
        actorlist.add(actorItem);
        actorItem = new ActorItem(R.drawable.jjangkim,"김어준","2020/04/06","찐");
        actorlist.add(actorItem);
        actorItem = new ActorItem(R.drawable.kbr,"김범룡","2020/04/06","진짜가수");
        actorlist.add(actorItem);
        actorItem = new ActorItem(R.drawable.kimdong,"이민호","2020/04/06","멋져");
        actorlist.add(actorItem);
        actorItem = new ActorItem(R.drawable.leemin,"이민호","2020/04/06","멋져");
        actorlist.add(actorItem);
        actorItem = new ActorItem(R.drawable.parksoccer,"박지성","2020/04/06","멋져");
        actorlist.add(actorItem);

        //2. 사용자 정의 어댑터 객체 생성
        ExamAdapter adapter = new ExamAdapter(this, R.layout.select_exam_row,actorlist);
        //3. ListView에 어댑터 연결
        listView.setAdapter(adapter);

    }
}
