package multi.android.network.android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import multi.android.network.R;

public class LedTest extends AppCompatActivity {
    Button btnon;
    Button btnoff;
    Socket socket;
    OutputStream os;
    InputStream is;
    PrintWriter pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_test);
        btnon = findViewById(R.id.led_on);
        btnoff = findViewById(R.id.led_off);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("70.12.226.101",5000);
                    if(socket!=null){
                        os = socket.getOutputStream();
                        pw = new PrintWriter(os,true);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        btnon.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        pw.println(1);
                    }
                }).start();
            }
        });
        btnoff.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        pw.println(0);
                    }
                }).start();
            }
        });
    }

}

