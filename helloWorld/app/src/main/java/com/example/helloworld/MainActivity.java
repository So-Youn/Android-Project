package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
//Activity의 LifeCycle
public class MainActivity extends AppCompatActivity {
    //Activity가 생성될 때 자동으로 호출.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // System.out.println("oncreate호출~~~~");  - logcat에서 알아보기 힘들다
        Log.d("test","onCreate()호출");   //tag : 로그에서 "test"만 조회 가능하다
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("test","onStart()호출");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("test","onResume()호출");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("test","onPause()호출");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("test","onDestroy()호출");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("test","onStop()호출");
    }

    //버튼을 클릭했을 때 실행할 메소드를 정의
    //메소드의 매개변수에 실행할 버튼을 정의
    //Button의 상위인 View타입으로 정의

    public void myclickMethod(View v){
        Toast.makeText(this, "확인 버튼이 눌렸습니다",
        Toast.LENGTH_LONG).show();//Toast : android의 alert //context로 정의된 자리에 this가 전달된다.
    }

}

