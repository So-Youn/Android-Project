package multi.android.material_design_pro.tab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import multi.android.material_design_pro.R;
import multi.android.material_design_pro.exam.FirstFragment;
import multi.android.material_design_pro.exam.ListTestFragment;
import multi.android.material_design_pro.exam.ThirdFragment;

public class TabTest extends AppCompatActivity {
    ArrayList<Fragment> viewlist = new ArrayList<Fragment>();
    FirstFragment firstFragment;
    ListTestFragment secondFragment;
    ThirdFragment thirdFragment;

    TabLayout tabLayout;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_test);
        firstFragment = new FirstFragment();
        secondFragment = new ListTestFragment();
        thirdFragment = new ThirdFragment();

        tabLayout = findViewById(R.id.mytab);
        bottomNavigationView = findViewById(R.id.bottom_navi);

        //탭 추가
        tabLayout.addTab(tabLayout.newTab().setText("설정"));

        //처음 실행할 때 보여줄 프래그먼트 지정
        getSupportFragmentManager().beginTransaction().
                replace(R.id.content_container,firstFragment).commit();

        //탭에 이벤트 연결하기 - setOnTabSelectedListener는 deprecated된 메서드로 addOn~ 써주기!

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           //탭이 선택될 때 호출되는 메소드
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition(); //탭의 순서 받아오기
                Log.d("tab",position+"");
                Fragment fragment;
                if(position==0){
                     fragment = firstFragment;
                }else if(position ==1){
                     fragment = secondFragment;
                }else {
                     fragment = thirdFragment;
                }
                //탭을 선택할 때 지정된 프래그먼트 객체가 show되도록 연결
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.content_container,fragment).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //BottomNavigationView이벤트 연결
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.bottom_item2){
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.content_container,secondFragment).commit();
                }
                return false;
            }
        });
    }
}
