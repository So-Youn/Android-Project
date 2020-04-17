package multi.android.material_design_pro2.recycler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import multi.android.material_design_pro2.R;

public class CardViewRecyclerTest extends AppCompatActivity {
    RecyclerView card_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview_recycler_test);
        card_list = findViewById(R.id.card_list);

        List<CardViewItem> recycle_card_data = new ArrayList<CardViewItem>();
        CardViewItem[] item = new CardViewItem[5];
        item[0] = new CardViewItem(R.drawable.lee,"이민호의 신의");
        item[1] = new CardViewItem(R.drawable.gong, "공유의 도깨비");
        item[2] = new CardViewItem(R.drawable.jang,"검색어를 입력하세요");
        item[3] = new CardViewItem(R.drawable.jung,"정우성의 비트");
        item[4] = new CardViewItem(R.drawable.so,"미안하다 사랑한다");
        for (int i=0;i<5;i++){
            recycle_card_data.add(item[i]);
        }
        CardViewAdapter adapter = new CardViewAdapter(this,
                R.layout.card_item,recycle_card_data);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        card_list.setHasFixedSize(true);
        card_list.setLayoutManager(manager); //vertical Linearlayout에 셋팅
        //4. Recycler와 Adapter를 연결
        card_list.setAdapter(adapter);
    }
}
