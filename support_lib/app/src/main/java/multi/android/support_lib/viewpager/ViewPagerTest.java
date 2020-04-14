package multi.android.support_lib.viewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.zip.Inflater;

import multi.android.support_lib.R;
/*
    화면 전환을 위해서 ViewPager를 사용하는 경우 (ListView와 동일)
    1. ViewPager에 담을 데이터 - View, Fragment
    2. Adapter 커스터마이징
    3. ViewPAger에 Adapter연결하기
 */
public class ViewPagerTest extends AppCompatActivity {
    // 1. ViewPager에 표시할 뷰를 저장할 List
    ArrayList<View> viewlist = new ArrayList<View>();
    ViewPager mainPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_test);
        mainPager = findViewById(R.id.mainpager);

        //ViewPager에 담을 View객체 생성
        LayoutInflater inflater = getLayoutInflater();

        View view1 = inflater.inflate(R.layout.view1,null);
        View view2 = inflater.inflate(R.layout.view2,null);
        View view3 = inflater.inflate(R.layout.view3,null);

        viewlist.add(view1);
        viewlist.add(view2);
        viewlist.add(view3);
        //3.ViewPager에 어댑터 연결
        MainPagerAdapter adapter = new MainPagerAdapter();
        mainPager.setAdapter(adapter);
    }
    class MainPagerAdapter extends PagerAdapter{
        //ViewPager를 통해 보여줄 뷰의 갯수 리턴
        @Override
        public int getCount() {
            return viewlist.size();
        }
        //ViewPager에 보여줄 뷰를 등록
        //getView와 같은 역할.
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            mainPager.addView(viewlist.get(position));
            return viewlist.get(position);
        }
        //매개변수로 전달된 뷰들끼리 비교
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;//집어넣은 view 와 return된 view 비교
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            mainPager.removeView((View)object);
        }
    }
}
