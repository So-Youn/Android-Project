package multi.android.datamanagementpro.sqllite.exam;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    String _id;
    String name;
    int price;
    public Product(){

    }

    public Product(String _id, String name, int price) {
        this._id = _id;
        this.name = name;
        this.price = price;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public Product(Parcel in){

        _id = in.readString();
        name = in.readString();
        price = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(name);
        dest.writeInt(price);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };




}
