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
    SimpleDateFormat nowDate = new SimpleDateFormat("yyyy,MM.DD", java.util.Locale.getDefault());
    String[] permissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    checkPermissions(permissions);


public void checkPermissions(String[] permissions) {
    ArrayList<String> list = new ArrayList<String>();
    for(int i=0;i<permissions.length;i++){
        String curPermission = permissions[i];
        int permissionCheck = ContextCompat.checkSelfPermission(this,curPermission);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            printToast
        }
}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_mgr);
        memoview = findViewById(R.id.memoview);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {  //int리턴
            permission_state = true;

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

    public void saveFile(View v) {
        printToast("저장버튼");
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            File external = Environment.getExternalStorageDirectory();

            String dirPath = external.getAbsolutePath() + "/" + nowDate + "mynote";
            File dir = new File(dirPath);

            if (!dir.exists()) {
                dir.mkdir();
            }
            FileWriter fw = null;
            try {
                fw = new FileWriter(dir + "/_memo.txt");
                fw.write("지금은 안드로이드 외부저장소에 파일을 저장하고 " +
                        "저장된 파일을 오픈하는 연습을 하는 시간입니다.");
               // openFile(v);
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
}







