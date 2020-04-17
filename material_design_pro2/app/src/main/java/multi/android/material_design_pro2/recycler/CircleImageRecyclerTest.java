package multi.android.material_design_pro2.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import multi.android.material_design_pro2.R;

public class CircleImageRecyclerTest extends AppCompatActivity {
    RecyclerView circle_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_image_recycler_test);
        circle_list = findViewById(R.id.circle_list);

        List<CircleItem> recycle_circle_data = new ArrayList<CircleItem>();
        CircleItem[] item = new CircleItem[5];
        item[0] = new CircleItem(R.drawable.lee);
        item[1] = new CircleItem(R.drawable.gong);
        item[2] = new CircleItem(R.drawable.jang);
        item[3] = new CircleItem(R.drawable.jung);
        item[4] = new CircleItem(R.drawable.so);
        for (int i=0;i<5;i++){
        recycle_circle_data.add(item[i]);
        }
        RecyclerCircleAdapter adapter = new RecyclerCircleAdapter(this,
                R.layout.circle_item,recycle_circle_data);
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(),2);
        circle_list.setHasFixedSize(true);
        circle_list.setLayoutManager(manager); //vertical Linearlayout에 셋팅
        //4. Recycler와 Adapter를 연결
        circle_list.setAdapter(adapter);
    }
}
