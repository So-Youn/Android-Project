package multi.android.intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ExamFirstActivity extends AppCompatActivity {
    private static final int SECOND_BUTTON = 1;
    private static final int OBJECT_INTENT = 1;
    TextView txtName;
    TextView txtTel;
    TextView returnTxt;
    Button btn1;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstexam);
        txtName = findViewById(R.id.EditText01);
        txtTel = findViewById(R.id.EditText02);
        returnTxt = findViewById(R.id.first_return);
        btn1 = (Button) findViewById(R.id.Button01);
        btn2 = (Button) findViewById(R.id.Button02);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //명시적 intent
                Intent intent = new Intent(ExamFirstActivity.this, ExamSecondActivity.class);
                intent.putExtra("name", txtName.getText().toString());
                //getText()는 Editable형태이기 때문에 toString처리 해주어야 한다.
                intent.putExtra("tel", txtTel.getText().toString());
                startActivityForResult(intent,SECOND_BUTTON);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //액티비티를 호출하면서 인텐트 객체를 공유
                Intent intent = new Intent(ExamFirstActivity.this, ExamSecondActivity.class);
                User dto = new User(txtName.getText().toString(),txtTel.getText().toString());
                intent.putExtra("dto",dto);
                startActivity(intent);
            }
        });
    }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent intent){
            super.onActivityResult(requestCode, resultCode, intent);
            if (requestCode == SECOND_BUTTON) {
                if (resultCode == RESULT_OK) {
                    boolean state = intent.getBooleanExtra("memState",false);
                    if(state){
                        returnTxt.setText("우수회원 설정");
                    }else{
                        returnTxt.setText("일반회원 설정");
                    }
                }
            }
        }
}