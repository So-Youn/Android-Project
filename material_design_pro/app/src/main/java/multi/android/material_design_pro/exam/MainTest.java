package multi.android.material_design_pro.exam;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;

import multi.android.material_design_pro.R;


public class MainTest extends AppCompatActivity {

    ArrayList<Fragment> viewlist = new ArrayList<Fragment>();
    String[] tablist ={"추천 여행지", "축제","나의 여행지"};

    FirstFragment firstFragment;
    ListTestFragment secondFragment;
    ThirdFragment thirdFragment;

    TabLayout tabLayout;
    ViewPager mainPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_exam);
        tabLayout = findViewById(R.id.mytab);
        mainPager = findViewById(R.id.mainpager);

        tabLayout.setTabTextColors(Color.CYAN,Color.BLACK);

        firstFragment = new FirstFragment();
        secondFragment = new ListTestFragment();
        thirdFragment = new ThirdFragment();


        viewlist.add(firstFragment);
        viewlist.add(secondFragment);
        viewlist.add(thirdFragment);

       MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(),
                viewlist.size());
       mainPager.setAdapter(adapter);
       tabLayout.setupWithViewPager(mainPager);

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

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tablist[position];
        }
    }

}
