package multi.android.material_design_pro.tab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import multi.android.material_design_pro.R;

public class TabTest2 extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager pager;
    //프레그먼트를 담을 Arraylist
    ArrayList<Fragment> fragmentArrayList = new ArrayList<Fragment>();
    //탭 문자열을 담을 ArrayList
    ArrayList<String> tabdatalist = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_test2);
        tabLayout = findViewById(R.id.tabs);
        pager = findViewById(R.id.pager);

        tabLayout.setTabTextColors(Color.CYAN,Color.WHITE);
        for(int i=1;i<=10;i++){
            ChildFragment fragment = new ChildFragment();
            fragment.setTitle("작업 중인 프래그먼트 :"+i);
            fragmentArrayList.add(fragment);
            tabdatalist.add("탭"+ i);
           // tabLayout.addTab(tabLayout.newTab().setText("탭"+i));
        }
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),
                                    fragmentArrayList.size());
        pager.setAdapter(adapter);

        //TabLayout과 ViewPager를 연결
        // - ViewPager의 getPageTitle메소드를 호출해서 탭의 문자열을 셋팅
       tabLayout.setupWithViewPager(pager);

        }
    class PagerAdapter extends FragmentStatePagerAdapter{ //오버라이딩 + 생성자
        public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        //뷰페이저와 탭을 연결하기 위해서 탭에 출력될 문자열을 만들어내는 메소드
        //getPageTitle 이 없다면 Tab과 연결된 문자열이 출력되지 않는다.
        //setupWithViewPager 메소드 내부에서 탭의 문자열을 출력하기 위해서 호출된다.
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabdatalist.get(position);
        }
    }
}
