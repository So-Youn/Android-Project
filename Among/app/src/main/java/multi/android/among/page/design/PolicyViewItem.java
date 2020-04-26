package multi.android.among.page.design;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import multi.android.among.page.PolicyFragment;

public class PolicyViewItem {
    int image;
    String name;
    String pre;
    public PolicyViewItem(int image, String name,String pre){
        this.image = image;
        this.name = name;
        this.pre = pre;
    }


    public int getImage() {
        return image;
    }


    public String getName() {
        return name;
    }


    public String getPre() {
        return pre;
    }

    @Override
    public String toString() {
        return "PolicyViewItem{" +
                "image=" + image +
                ", name='" + name + '\'' +
                ", pre='" + pre + '\'' +
                '}';
    }

}
