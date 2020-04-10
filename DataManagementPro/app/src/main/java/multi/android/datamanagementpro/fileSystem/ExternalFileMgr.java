package multi.android.datamanagementpro.fileSystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ExpandedMenuView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import multi.android.datamanagementpro.R;

public class ExternalFileMgr extends AppCompatActivity {
    boolean permission_state;
    TextView view;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_file_mgr);
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){  //int리턴
            permission_state =true;
            printToast("쓰기 권한이 설정되었습니다.");

        }else{
            permission_state = false;
            printToast("권한을 설정해야 합니다.");
            //2.권한이 없는 경우 권한을 설정하는 메서드를 띄운다.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1000); //ActivityCompat : 상위 클래스
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                               @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1000 && grantResults.length>0){  //권한의 성공 설정에 대한 결과가 있다는 의미

            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){ //카메라에 대한 권한
                permission_state = true; //허용
                printToast("권한 설정 마무리 완료");
            }else{
                printToast("권한 설정을 하지 않았으므로 기능 사용 불가");
            }
        }
    }
    private void printToast(String s) {
        Toast.makeText(this,s ,Toast.LENGTH_LONG).show();
    }
    public void saveExternalFileSystem (View v){
        if(permission_state){
            printToast("권한 설정완료");
            String state = Environment.getExternalStorageState();
            if(state.equals(Environment.MEDIA_MOUNTED)){ // 현재 사용 가능한 상태
                printToast("사용 가능");
                File external = Environment.getExternalStorageDirectory();
                //외부저장소/임의의 디렉토리를 생성 - 앱을 삭제해도 데이터는 남아있다.
                // String dirPath = external.getAbsolutePath()+"/myapp"; // 경로 정보 가져오기
                //외부 저장소/android/data/앱의 패키지명으로 디렉토리 생성
                //=> 앱을 삭제하면 데이터가 같이 삭제된다.

                String dirPath = external.getAbsolutePath();
               String pkg = getPackageName();
               File dir = new File(dirPath+"/android/data/"+pkg);
                //디렉토리가 없으면 생성
                //File dir = new File(dirPath); // dirPath/파일명
                if(!dir.exists()){  //없을 때 (false)
                    dir.mkdir();
                }
                FileWriter fw = null;
                try {
                    fw = new FileWriter(dir+"/test1.txt");
                    fw.write("외부 저장소 테스트 중 ");

                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if(fw!=null){
                            fw.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                printToast("사용 불가능");
            }
        }else{
            printToast("권한 설정하자");
        }
    }
}
