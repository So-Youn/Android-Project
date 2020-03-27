package com.day03.view.advancedview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.nio.file.WatchEvent;

public class LoginActivity extends AppCompatActivity {

    EditText mem_id;
    EditText pass;
    Button send;
    Button btnlog;
    Button btnsign;
    Button btndetail;

    LinearLayout linear1;
    LinearLayout linear2;
    LinearLayout linear3;

    View detail;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.framelayout_test);
        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);
        linear3 = findViewById(R.id.linear3);

        mem_id = findViewById(R.id.mem_id);
        pass = findViewById(R.id.mem_pass);
        send = findViewById(R.id.confirm);

        btnlog = findViewById(R.id.btnlog);
        btnsign = findViewById(R.id.btnsign);
        btndetail = findViewById(R.id.btndetail);


    }

    //버튼이 클릭될 때 호출되는 메소드 = View.OnClickListener의
    //public void onclick(View v)의 메소드와 동일한 역할
    public void myclick(View v) {
        if (v.getId() == R.id.button) {
            linear1.setVisibility(View.VISIBLE);
            linear2.setVisibility(View.INVISIBLE);
            linear3.setVisibility(View.INVISIBLE);
        } else if (v.getId() == R.id.button2) {
            linear1.setVisibility(View.INVISIBLE);
            linear2.setVisibility(View.VISIBLE);
            linear3.setVisibility(View.INVISIBLE);
        } else if (v.getId() == R.id.button3) {
            linear1.setVisibility(View.INVISIBLE);
            linear2.setVisibility(View.INVISIBLE);
            linear3.setVisibility(View.VISIBLE);
        }
    }


};






