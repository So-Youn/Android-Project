package multi.android.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class ExamSecondActivity extends AppCompatActivity {
    Button btnclose;
    String data ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_secondview);
        btnclose = (Button)findViewById(R.id.exam_close);
        final TextView txt = findViewById(R.id.exam_result_txt);
        final CheckBox memChk = findViewById(R.id.member_state);
        final Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        if(name==null){ //아이디가 없다는 것은 parcelable로 갖고 온 것
            User dto = intent.getParcelableExtra("dto");
            txt.setText(dto.name+","+dto.getTelNum());
        }else{
            String tel = intent.getStringExtra("tel");
            txt.setText("입력한 이름 :"+name+" 입력한 전화번호 : "+tel+ " ");
        }


        btnclose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                intent.putExtra("memState",memChk.isChecked());
                setResult(RESULT_OK,intent);
                finish();
            }
        });



    }
}
