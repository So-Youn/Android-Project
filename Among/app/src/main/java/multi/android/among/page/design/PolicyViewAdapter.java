package multi.android.among.page.design;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import multi.android.among.R;
import multi.android.among.page.MainActivity;
import multi.android.among.page.PolicyFragment;

public class PolicyViewAdapter
        extends RecyclerView.Adapter<PolicyViewAdapter.ViewHolder>{
    PolicyFragment context;
    //MainActivity activity =(MainActivity) context.getActivity();
    int row_id;
    List<PolicyViewItem> data;
    public PolicyViewAdapter(PolicyFragment context, int row_id, List<PolicyViewItem> data){
        this.context = context;
        this.row_id = row_id;
        this.data = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(row_id,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView image = holder.image;
        TextView name = holder.name;
        TextView pre = holder.pre;
        image.setImageResource(data.get(position).getImage());
        name.setText(data.get(position).getName());
        pre.setText(data.get(position).getPre());
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(,"데이터 연결 완료",
                        Toast.LENGTH_SHORT).show();*/
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        TextView pre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            pre = itemView.findViewById(R.id.pre);
        }
    }
}
