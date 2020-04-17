package multi.android.material_design_pro2.recycler;

import android.view.View;

public class CardViewItem {
    int image;
    String name;
    public CardViewItem(int image,String name) {
        this.image = image;
        this.name = name;
    }


    public int getImage() {
        return image;

    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CardViewItem{" +
                "image=" + image +
                ", name='" + name + '\'' +
                '}';
    }


}
