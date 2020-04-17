package multi.android.material_design_pro2.recycler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import multi.android.material_design_pro2.R;

//RecyclerVeiw에서 사용하는 Adapter를 커스트마이징
//Adapter안에 ViewHolder포함 - 정의 (ListView 사용할 때와 동일한 역할)
/*            ----------
                  ^
                   \_Inner class로 정의
 */
public class SimpleitemAdapter
        extends RecyclerView.Adapter<SimpleitemAdapter.ViewHoler> {
    Context context;
    int row_res_id; // row를 구성하는 layout
    List<SimpleItem> data; // RecyclerView에 출력될 전체 데이터

    public SimpleitemAdapter(Context context, int row_res_id, List<SimpleItem> data) {
        this.context = context;
        this.row_res_id = row_res_id;
        this.data = data;
    }
    //xml로 부터 뷰를 만들어서 ViewHolder넘기는 작업
    //View를 구성하는 구성요소의 리소스를 가져오는 작업을 하는 객체

    //1. onCreateViewHolder에서 Row에 대한 view를 inflate해서 생성
    //2. ViewHolder 객체를 만들어서 1번에서 생성한 뷰를 넘긴다.
    //3. ViewHolder 객체 안에서 onCreateViewHolder메소드에서 리턴받은 객체에서 데이터를
    //   연결할 뷰를 찾는다(findViewById)
    //4. onBindViewHolder가 갖고있는 구성요소에 데이터를 연결하기

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent,
                                        int viewType) {
        View view = LayoutInflater.from(context).inflate(row_res_id,null);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        Log.d("recycler","onBindViewHolder"+position);
        //ViewHolder가 찾아놓은 TextView를 꺼내고
        TextView row_text_view = holder.textView;
        //꺼낸 TextView에 데이터 연결
        row_text_view.setText(data.get(position).getData());
        //TextView에 클릭이벤트 연결
        row_text_view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(context,"데이터 연결 완료",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    //RecyclerView에 출력할 데이터의 갯수 리턴
    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHoler extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);// 전달받은 row 객체
            textView = itemView.findViewById(R.id.itemview);

        }
    }
}
