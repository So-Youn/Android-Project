package multi.android.intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class BasicAppRun extends AppCompatActivity {
    //승인받을 권한의 목록
    //권한 - String 상수
    String[] permission_list = {
            Manifest.permission.CALL_PHONE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_app_run);
        //Activity가 만들어 질 때 권한 체크를 하는 메서드를 호출
        runPermission();
    }
    //구글 맵 실행
    public void runGoogleMap(View v){
        Uri uri = Uri.parse("geo:37.501579,127.039585"); //위도 경도 값 넘겨주기
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
    //웹 브라우저 실행
    public void runWeb(View v){
        Uri uri = Uri.parse("https://www.daum.net");  // : 기준으로 기능이 다르다.
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    //전화걸기 화면 실행
    public void runDial(View v){
        Uri uri = Uri.parse("tel:00000000");  // : 기준으로 기능이 다르다.
        Intent intent = new Intent(Intent.ACTION_DIAL,uri);
        startActivity(intent);
    }
    //실제 전화 걸기위한 메서드
    public void runCallPhone(View v){
        Intent intent = null;
        int chk = PermissionChecker.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE);
        if(chk==PackageManager.PERMISSION_GRANTED){
            //권한 허가 됐을 때
            Log.d("tel", "성공");
            intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:01027473963"));
        }else{
            Log.d("tel","실패");
        }
        startActivity(intent);
    }
    //권한을 체크 - 승인 처리
    public void runPermission(){
    //하위 버전이면 실행되지 않도록 처리
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return;
        }
        //모든 권한을 셀프 체크
        for (String permission:permission_list){
            int chk = checkCallingOrSelfPermission(permission);
            if(chk == PackageManager.PERMISSION_DENIED){
                requestPermissions(permission_list,0);
                break;
            }
        }
    }
}
