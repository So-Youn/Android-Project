package multi.android.material_design_pro2.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import multi.android.material_design_pro2.R;

public class CardViewAdapter
        extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
    Context context;
    int row_id;
    List<CardViewItem> data;

    public CardViewAdapter(Context context, int row_id, List<CardViewItem> data) {
        this.context = context;
        this.row_id = row_id;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(row_id,null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView cardImage = holder.cardImage;
        TextView name = holder.name;
        cardImage.setImageResource(data.get(position).getImage());
        name.setText(data.get(position).getName());
        cardImage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(context,"데이터 연결 완료",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView cardImage;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.cardImage);
            name = itemView.findViewById(R.id.name);
        }
    }

}
