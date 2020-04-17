package multi.android.material_design_pro2.recycler;

public class CircleItem {
    int image;

    public CircleItem(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "CircleItem{" +
                "image=" + image +
                '}';
    }
}
