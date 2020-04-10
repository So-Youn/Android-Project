package multi.android.datamanagementpro.fileSystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import multi.android.datamanagementpro.R;

public class MemoMgr extends AppCompatActivity {
    boolean permission_state;
    EditText memoview;
    Date date = new Date();
    SimpleDateFormat nowDate = new SimpleDateFormat("yyyyMMDD");
    String time = nowDate.format(date);
    String[] permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_mgr);
        memoview = findViewById(R.id.memoview);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {  //int리턴
            permission_state = true;
            printToast("권한 설정");

        } else {
            permission_state = false;
            printToast("권한을 설정해야 합니다.");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1000);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permission_state = true; //허용
                printToast("권한 설정 마무리 완료");

            } else {
                printToast("권한 설정을 하지 않았으므로 기능 사용 불가");
            }
        }
    }

    public void printToast(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void saveFile(View v) throws IOException {
        String text = memoview.getText().toString();
        printToast("저장버튼");
        if(permission_state){
            printToast("권한 설정 O");
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                File external = Environment.getExternalStorageDirectory();

                String dirPath = external.getAbsolutePath() + "/Android/mynote";
                File dir = new File(dirPath);
                openFile(v);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                FileWriter fw = null;
                try {
                    fw = new FileWriter(dir + "/"+time+"_memo.txt");
                    fw.write(text);

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fw != null) {
                            fw.close();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }


    }


    public void openFile(View view) throws IOException {
        StringBuffer sb = new StringBuffer();
        File external = Environment.getExternalStorageDirectory();
        String dirPath = external.getAbsolutePath()+"/Android/mynote";

        InputStream input = new FileInputStream(dirPath+"/"+time+"_memo.txt");
        printToast("열기");
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String line = "";
        StringBuffer data = null;
        while ((line = br.readLine())!=null){
            data = sb.append(line+"\n");
        }
        memoview.setText(data);
    }

    public void newFile(View v){
        memoview.setText("  ");
    }

}










