package exam.day03.view.selectview.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import exam.day03.view.selectview.R;

public class InflatorTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflator_test);
        Button btn = findViewById(R.id.btnAdd);
        final LinearLayout container = findViewById(R.id.container);
        //버튼을 누르면 include_view.xml을 파싱해서 붙이는 작업

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                 //Context.상수명 - 해당 객체 접근 가능
                //getSystemService : Context가 가지고 있는 메서드
                inflater.inflate(R.layout.include_view,container,true);
                //return이 아닌 지정한 view에 붙이는 작업
            }
        });
    }
}
