package multi.android.support_lib.viewpager.exam;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import multi.android.support_lib.R;

public class MainTest extends AppCompatActivity {

    ArrayList<Fragment> viewlist = new ArrayList<Fragment>();

    ViewPager mainPager;
    FirstFragment firstFragment;
    //SecondFragment secondFragment = new SecondFragment();
    ListTestFragment secondFragment;
    ThirdFragment thirdFragment;
    MapFragment forthFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_exam);
        mainPager = findViewById(R.id.mainpager);

        firstFragment = new FirstFragment();
        secondFragment = new ListTestFragment();
        thirdFragment = new ThirdFragment();
        forthFragment = new MapFragment();

        viewlist.add(firstFragment);
        viewlist.add(secondFragment);
        viewlist.add(thirdFragment);
        viewlist.add(forthFragment);


        MainPagerAdapter adapter
                = new MainPagerAdapter(getSupportFragmentManager(),viewlist.size());
        mainPager.setAdapter(adapter);
        mainPager.addOnPageChangeListener(new PageListener());

    }
public void btn_click(View view){
        mainPager.setCurrentItem(Integer.parseInt(view.getTag().toString()));
}
    class MainPagerAdapter extends FragmentStatePagerAdapter {

        public MainPagerAdapter(FragmentManager fm,int behavior) {
            //behavior : 몇 번 동작 해야하는지
            super (fm,behavior);
        }


        @Override
        public Fragment getItem(int position) {
            return viewlist.get(position);
        }


        @Override
        public int getCount() {
            return viewlist.size();
        }


    }
    class PageListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //페이지가 변경됐을 때
            Toast.makeText(MainTest.this, "페이지가 전환됐음",Toast.LENGTH_LONG).show();

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
