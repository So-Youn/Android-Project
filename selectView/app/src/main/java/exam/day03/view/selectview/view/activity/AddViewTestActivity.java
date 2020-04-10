package exam.day03.view.selectview.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import exam.day03.view.selectview.R;

public class AddViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL); //수직 배치
       //Layout만들기 - width, height 지정
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
       //Layout에 추가할 view 생성 - 상위 뷰의 크기 정보를 갖고있는
        //LayoutParams를 생성
        Button btn = new Button(this);   //context를 상속받기 때문에 Button이 context
        btn.setText("코드로 만들어진 버튼");
        btn.setLayoutParams(params);

        //Layout 에 뷰를 추가
        layout.addView(btn);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {   //익명 innerClass 이므로 this만 호출 시 에러발생
                Button btn2 = new Button(AddViewTestActivity.this); //outerClass 지칭
                btn2.setText("이벤트로 만들어진 객체");
                layout.addView(btn2);
            }
        });
        setContentView(layout);

    }
    }