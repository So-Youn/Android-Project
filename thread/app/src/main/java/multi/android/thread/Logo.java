package multi.android.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
//5초 후에 처리해야 하는 작업을 쓰레드 정의
public class Logo extends AppCompatActivity {
    Handler handler;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(Logo.this,HandlerExam2.class);
            startActivity(intent);
            finish(); //이동하고 난 다음에는 현재 activity가 없어져야 한다.
            //메인 액티비티로 전환될 때 애니메이션 효과를 가진다.
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        handler = new Handler();
        handler.postDelayed(runnable,5000);
    }
}
