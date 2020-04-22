package multi.android.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class HandlerExam extends AppCompatActivity {
    TextView textView;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_exam);
        textView = findViewById(R.id.num);

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what==1){
                    int val = msg.arg1;
                    textView.setText(val+" ");
                }
            }
        };
    }
    public void btnNumOut(View view){
        new Thread(new Runnable() {

            @Override
            public void run() {
            for(int i=1;i<=100;i++){
                Message msg = new Message();
                msg.what = 1;
                msg.arg1 = i;
                handler.sendMessage(msg);
                SystemClock.sleep(100);
            }
            }
        }).start();
    }
}
