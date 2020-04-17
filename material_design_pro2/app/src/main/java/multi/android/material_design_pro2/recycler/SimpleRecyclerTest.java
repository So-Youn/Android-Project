package multi.android.material_design_pro2.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import multi.android.material_design_pro2.R;

public class SimpleRecyclerTest extends AppCompatActivity {
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_recycler_test);
        list = findViewById(R.id.list);
        //1. Recycler에 츌력할 데이터 준비 ( db or local 저장소)
        List<SimpleItem> recycler_simple_data = new ArrayList<SimpleItem>();

        for (int i=0; i<10;i++){
            SimpleItem item = new SimpleItem("simple_item"+i);
            recycler_simple_data.add(item);
        }
        //2. Adapter 생성
        SimpleitemAdapter adapter = new SimpleitemAdapter(
                this, R.layout.simple_item,recycler_simple_data);

        //3. Recycler에 레이아웃을 설정
        //   LinearLayout, GridLayout
        // RecyclerView에 설정할 리니어 레이아웃 객체 생성
        /*LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);*/
        //GridLayout 설정
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(),2);
        //spanCount : 열로 2줄 출력
        list.setHasFixedSize(true);
        list.setLayoutManager(manager); //vertical Linearlayout에 셋팅
        //4. Recycler와 Adapter를 연결
        list.setAdapter(adapter);

        //5. 추가적인 요소들을 적용할 수 있다. - 꾸미기, 애니메이션, ...



    }
}
