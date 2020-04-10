package soyun.test.com;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onButton1Clicked(View v){
        Toast.makeText(getApplicationContext(),"버튼이 눌렸습니당.",Toast.LENGTH_LONG).show();
        //Toast라는 메시지가 뜨도록.
    }
    public void onButton2Clicked(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(intent);
    }
    public void onButton3Clicked(View v){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
