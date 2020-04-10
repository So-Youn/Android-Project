package exam.day03.view.selectview.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import exam.day03.view.selectview.R;

public class MyAdapter extends ArrayAdapter<User> {
    //기존에 만들어진 class 상속받아야 함.
    private Context context;
    private int resId;
    private ArrayList<User> datalist;


    public MyAdapter( Context context, int resId,  ArrayList<User> datalist) {
        super(context, resId, datalist);
        this.context = context;
        this.resId = resId;
        this.datalist = datalist;
    }
    //리스트 갯수를 반환
    @Override
    public int getCount() {
        return datalist.size();
    }

    //매개변수로 전달받은 순서에 있는 리스트 항목을 반환
    @Override
    public User getItem(int position) {
        return datalist.get(position);
    }
    //리스트의 한 항목을 만들 때 호출되는 메소드
    //즉,  리스트 항목이 100개면 100번 호출
    // position => 리스트 순서
    // convertView => 한 항목에 대한 뷰

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        Log.d("getview","getView:"+position);
        long start = System.nanoTime(); // 뷰 만들기 전 시간 측정
        //뷰를 생성
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //getSystemService는 Context 메서드이기 때문에
        //convertView : 리스트에서 보여지는 row 에 대한 view
        convertView = inflater.inflate(resId, null); // 뷰 만들기 inflate(int,viewGroup)


        //ArrayList에서 리턴된 리스트 항목의 번호와 동일한 데이터를 구하기
        User user = datalist.get(position);
        //위에서 생성한 뷰의 각 요소에 데이터를 연결
        ImageView imageView = convertView.findViewById(R.id.img);
        TextView nameView = convertView.findViewById(R.id.txtcust1);
        TextView telNumView = convertView.findViewById(R.id.txtcust2);

        imageView.setImageResource(user.myImg);
        nameView.setText(user.name);
        telNumView.setText(user.telNum);
        long end = System.nanoTime();
        Log.d("getview",(end-start)+"");
        return convertView;
    }

}
