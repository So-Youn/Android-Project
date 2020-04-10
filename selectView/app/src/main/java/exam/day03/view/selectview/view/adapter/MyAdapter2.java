
package exam.day03.view.selectview.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import exam.day03.view.selectview.R;
//성능 개선을 위한 작업 추가
// - 1. 한번 만든 뷰는 재사용
// - 2.findViewById 한 번 작업한 뷰에 대한 정보는 저장해 놓고 다시 사용
public class MyAdapter2 extends ArrayAdapter<User> {
    private Context context;
    private int resId;
    private ArrayList<User> datalist; //데이터 저장

    //row마다 사용자가 설정한 값을 position과 함께 저장
    //해당 position에 대한 설정 값을 같이 출력
    //저장하는 시점은 사용자가 설정을 끝낸 시점 - focus를 잃어버리는 시점
    HashMap<Integer,SaveUserState> saveData = new HashMap<Integer, SaveUserState>();
    //데이터가 하나면 <Integer,String>으로 해주어도 된다.

    public MyAdapter2(Context context, int resId, ArrayList<User> datalist) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        Log.d("getview","getView:"+position);
        long start = System.nanoTime(); // 뷰 만들기 전 시간 측정
        //뷰를 생성 - 매개변수로 전달되는 converView를 재사용


        UserViewHolder holder = null;
        if(convertView ==null) {//한번도 만들어 진 적이 없을 때만(null)  inflate 시킨다.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null); // 뷰 만들기 inflate(int,viewGroup)

            //============뷰를 만드는 최초 작업이므로 뷰를 찾아서 가져오기==============
            holder = new UserViewHolder(convertView);
            // 최초 작업인 경우 홀더를 저장
            convertView.setTag(holder);
        }else{
            //=============최초 작업이 아님 ( 재사용 중이라면 )=====================
            holder = (UserViewHolder)convertView.getTag();
        }

        //ArrayList에서 리턴된 리스트 항목의 번호와 동일한 데이터를 구하기
        User user = datalist.get(position);
        if(user!=null) {
            //위에서 생성한 뷰의 각 요소에 데이터를 연결
            ImageView imageView = holder.myImg;
            TextView nameView = holder.nameView;
            TextView telNumView = holder.telNumView;
            final EditText editView = holder.editView;
            //innerclass를 outer에서 호출 할 때 final 꼭 붙여주기...

            imageView.setImageResource(user.myImg);
            nameView.setText(user.name);
            telNumView.setText(user.telNum);

            //뷰를 만들 때 저장된 내용이 있는지 체크해서 값을 출력하기
            SaveUserState state = saveData.get(position);
            if(state==null){
                //저장된 객체가 없으면
                editView.setText("");
            }else{ // 저장된 객체가 있으면 객체에서  data를 추출해서 출력
               // editView.setText(state.data);
            }


            //무언가를 작성했을 때만 실행되는 코드드
            //EditText focus를 잃어버리는 시점에 입력한 데이터를 저장
            editView.setOnFocusChangeListener(new View.OnFocusChangeListener(){

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    //focus 있을 때 true, 잃어버렸을 때 false
                    if (!hasFocus){
                        String data = editView.getText().toString();
                        SaveUserState objstate = new SaveUserState();
                      //  objstate.data = data;
                        saveData.put(position,objstate);
                    }

                }
            });
        }

        long end = System.nanoTime();
        Log.d("getview",(end-start)+"");
        return convertView;
    }

}

