
package multi.connect.carnetnavi;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapView;

import de.nitri.gauge.Gauge;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TMapGpsManager.onLocationChangedCallback {
    private final String TMAP_API_KEY = "l7xx69415d661c8445a8b35bd80789e07ebf";
    Button btn30, btn60, btn90;
    ImageButton up, down;
    Gauge speedometer;
    //   TMapGpsManager tmapgps;
    TMapView tmapview;
    float speed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setGPS();
        speedometer = findViewById(R.id.myGauge);
        up = findViewById(R.id.btnUp);
        up.setOnClickListener(this);
        down = findViewById(R.id.btnDown);
        down.setOnClickListener(this);
        btn30 = findViewById(R.id.btn30);
        btn30.setOnClickListener(this);
        btn60 = findViewById(R.id.btn60);
        btn60.setOnClickListener(this);
        btn90 = findViewById(R.id.btn90);
        btn90.setOnClickListener(this);
        speedometer.setLowerText("0");




        LinearLayout linearLayoutTmap = findViewById(R.id.layoutMapView);
        tmapview = new TMapView(this);
        tmapview.setSKTMapApiKey(TMAP_API_KEY);

        TMapMarkerItem markerItem1 = new TMapMarkerItem();
        markerItem1.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
        // 지도에 마커 추가
        tmapview.addMarkerItem("markerItem1", markerItem1);
        //현재 보는 방향
        tmapview.setCompassMode(true);
        tmapview.setZoomLevel(20);
        tmapview.setIconVisibility(true);

        TMapGpsManager gps = new TMapGpsManager(this);
        gps.setMinTime(1000);
        gps.setMinDistance(5);
        gps.setProvider(gps.GPS_PROVIDER);
        Log.d("msg", gps.toString());
        linearLayoutTmap.addView(tmapview);


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnUp) {
            if (speed <= 237) {
                speed = speed + 3;
            } else if (speed > 237) {
                speed = 240;
            }
            speedometer.moveToValue(speed);
            speedometer.setLowerText(Integer.toString((int) speed));
        } else if (v.getId() == R.id.btnDown) {
            if (speed >= 3) {
                speed = speed - 3;
            } else if (speed < 3) {
                speed = 0;
            }
            speedometer.moveToValue(speed);
            speedometer.setLowerText(Integer.toString((int) speed));
        } else if (v.getId() == R.id.btn30) {
            speed = 30;
            speedometer.moveToValue(speed);
            speedometer.setLowerText(Integer.toString((int) speed));
        } else if (v.getId() == R.id.btn60) {
            speed = 60;
            speedometer.moveToValue(speed);
            speedometer.setLowerText(Integer.toString((int) speed));
        } else if (v.getId() == R.id.btn90) {
            speed = 90;
            speedometer.moveToValue(speed);
            speedometer.setLowerText(Integer.toString((int) speed));
        }
    }


    private final LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {

            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                tmapview.setLocationPoint(longitude, latitude);
                tmapview.setCenterPoint(longitude, latitude);

            }

        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    public void setGPS() {
        final LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1); //위치권한 탐색 허용 관련 내용
            } else {


                lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자(실내에선 NETWORK_PROVIDER 권장)
                        1000, // 통지사이의 최소 시간간격 (miliSecond)
                        1, // 통지사이의 최소 변경거리 (m)
                        mLocationListener);

            }


        }
    }

    @Override
    public void onLocationChange(Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();
    }
}
