package exam.day03.view.selectview.view.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;


public class ExamAdapter extends ArrayAdapter<ActorItem> {
    private Context context;
    private int resId;
    private ArrayList<ActorItem> actorlist;

    HashMap<Integer,SaveUserState> saveData = new HashMap<Integer, SaveUserState>();

    public ExamAdapter(Context context, int resId, ArrayList<ActorItem> actorlist) {
        super(context, resId, actorlist);
        this.context = context;
        this.resId = resId;
        this.actorlist = actorlist;
    }



    public int getCount() {
        return actorlist.size();
    }


    @Override
    public ActorItem getItem(int position) {
        return actorlist.get(position);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        long start = System.nanoTime();
        if(convertView ==null) {//한번도 만들어 진 적이 없을 때만(null)  inflate 시킨다.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null); // 뷰 만들기 inflate(int,viewGroup)

            //============뷰를 만드는 최초 작업이므로 뷰를 찾아서 가져오기==============
            holder = new ViewHolder(convertView);
            // 최초 작업인 경우 홀더를 저장
            convertView.setTag(holder);
        }else{
            //=============최초 작업이 아님 ( 재사용 중이라면 )=====================
            holder = (ViewHolder)convertView.getTag();
        }

        //ArrayList에서 리턴된 리스트 항목의 번호와 동일한 데이터를 구하기
        ActorItem actor = actorlist.get(position);
        if(actor!=null) {
            //위에서 생성한 뷰의 각 요소에 데이터를 연결
            ImageView imageView = holder.atImg;
            TextView nameView = holder.nameView;
            TextView dateView = holder.dateView;
            TextView dataView = holder.dataView;
            final CheckBox checkbox = holder.checkbox;
            //innerclass를 outer에서 호출 할 때 final 꼭 붙여주기...

            imageView.setImageResource(actor.image);
            nameView.setText(actor.name);
            dateView.setText(actor.date);
            dataView.setText(actor.data);

            //뷰를 만들 때 저장된 내용이 있는지 체크해서 값을 출력하기
            SaveUserState state = saveData.get(position);

            if(state==null){
                //저장된 객체가 없으면
                checkbox.setChecked(false);
            }else{ // 저장된 객체가 있으면 객체에서  data를 추출해서 출력
                checkbox.setChecked(state.data);
            }


            checkbox.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    boolean data = checkbox.isChecked();
                    SaveUserState objstate = new SaveUserState();
                    objstate.data = data;
                    saveData.put(position,objstate);
                }
            });

        }

        long end = System.nanoTime();
        Log.d("getview",(end-start)+"");
        return convertView;
    }
}




