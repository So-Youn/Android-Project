package exam.day03.view.selectview.view.adapter;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import exam.day03.view.selectview.R;

public class ViewHolder {
    ImageView atImg;
    TextView nameView;
    TextView dateView;
    TextView dataView;
    CheckBox checkbox;

    public ViewHolder(View parentView) {
        this.atImg = parentView.findViewById(R.id.atImg);
        this.nameView = parentView.findViewById(R.id.nameView);
        this.dateView = parentView.findViewById(R.id.dateView);
        this.dataView = parentView.findViewById(R.id.dataView);
        this.checkbox = parentView.findViewById(R.id.checkbox);
    }
}
