package multi.android.datamanagementpro.permission;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.ActivityCompat;
        import androidx.core.content.ContextCompat;

        import android.Manifest;
        import android.content.Context;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.view.View;
        import android.widget.Toast;

        import multi.android.datamanagementpro.R;

public class RuntimePermissionTest extends AppCompatActivity {
    //퍼미션의 상태를 저장할 변수
    boolean permission_state;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtime_permission_test);
        // 1. Permission을 먼저 체크

    }
    //3. requestPermission의 메시지 창에서 선택한 후 호출되는 메서드
    // 결과를 리턴 - 결과에 따라 다르게 처리할 수 있도록 구현
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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

    public void printToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
    public void runCamera(View v){
        if(permission_state){


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
        }else{
            printToast("권한을 설정해야 이 기능을 쓸 수 있다.");
            //권한을 설정할 수 있는 액티비티로 자동 이동되도록

        }
    }
}
