package exam.day03.view.selectview.view.adapter;

public class ActorItem {

    int image;
    String name;
    String date;
    String data;


    public ActorItem(int image, String name, String date, String data) {
        this.image = image;
        this.name = name;
        this.date = date;
        this.data = data;

    }

    @Override
    public String toString() {
        return "ActorItem{" +
                "image=" + image +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
