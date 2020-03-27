package com.day03.view.advancedview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TextTestActivity extends AppCompatActivity {
    //layout페이지에서 사용할 view에 대한 리소스를 가져와야 하므로 변수 선언
    EditText textinfo;
    TextView lblinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_test);
        lblinfo = findViewById(R.id.myinfo);
        textinfo = findViewById(R.id.mytext);

        Button btngetter = findViewById(R.id.btnget);
        Button btnsetter = findViewById(R.id.btnset);

        btngetter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String msg = textinfo.getText() + "";
                lblinfo.setText(msg);

            }
        });
        btnsetter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textinfo.setText("가져온 문자열 :작업 완료");
            }
        });

        //EditText에서 데이터를 입력할 때 이벤트를 발생

        watcherListener listner = new watcherListener();
        textinfo.addTextChangedListener(listner);
    }

    class watcherListener implements TextWatcher {
        //문자 값이 변경되었을 때 호출
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        //문자의 값이 변경되었을 때 호출
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.d("watcher","s:"+", start :"+start);
            lblinfo.setText("문자열이 변경되고 있음....."+s);
        }
        //문자값이 변경된 후에 호출
        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
// WatcherListner listner = new WatcherListener();
//        txtinfo.addText