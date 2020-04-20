package multi.android.map_location_pro.location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import multi.android.map_location_pro.R;

//현재 위치정보를 가져와서 맵에 연결해서 출력 - avd, device
public class LocationMapExam extends AppCompatActivity implements OnMapReadyCallback, LocationListener {
    LocationManager locationManager;
    boolean permission_state;
    List<String> provider_list; //전체 위치 제공자 목록
    List<String> enableProvider_list; // 사용가능한
    GoogleMap map;

    Location location;
    MarkerOptions markerOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_map_exam);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED |
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {  //int리턴
            printToast("권한이 없습니다. ");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    1000);

        } else {
            permission_state = true;
            printToast("권한을 설정했습니다.");
            getLocation();


        }
        FragmentManager manager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) manager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                    & grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                permission_state = true; //허용
                printToast("권한 설정 마무리 완료");

            } else {
                printToast("권한 설정을 하지 않았으므로 기능 사용 불가");
            }
        }
    }

    public void getLocation() {
        enableProvider_list = locationManager.getProviders(true);
        for (String provider : enableProvider_list) {
            Location location;
            try {
                location = locationManager.getLastKnownLocation(provider);
                if (location != null) {
                    //위치 정보 : 위도 / 경도 / 고도

                    //이벤트 연결
                    locationManager.requestLocationUpdates(provider, 3000, 1, this);
                    //3초마다 한 번씩 위치 호출 - 실시간으로 데이터 수집
                }
                Log.d("msg", "======== 성 공 =======");
            } catch (SecurityException e) {
                Log.d("msg", "=========" + e.getMessage() + "=======");
            }

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (map != null) {

            for (String provider : enableProvider_list) {
                try {
                    permission_state = true;
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(location!=null){
                        markerOptions = new MarkerOptions();
                        map.getUiSettings().setMyLocationButtonEnabled(true);
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        LatLng latLng = new LatLng(latitude, longitude);
                        map.getUiSettings().setMyLocationButtonEnabled(true);
                        //지도 확대 축소버튼을 추가
                        CameraPosition.Builder builder = new CameraPosition.Builder(); //카메라에 대한 정보 담고있는 객체 builder 셋팅
                        builder.target(latLng); //어디를 중앙으로
                        builder.zoom(15);   //zoom레벨 설정
                        CameraPosition position = builder.build();
                        map.moveCamera(CameraUpdateFactory.newCameraPosition(position));
                        markerOptions.position(latLng);
                        map.addMarker(markerOptions);  //마커가 생성되어 map이 추가


                    }else{
                        Log.d("msg","location 객체 실패");
                    }

                } catch (SecurityException e) {
                    Log.d("msg", "=========" + e.getMessage() + "=======");
                }
            }
        }



    }
    public void printToast (String msg){

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}