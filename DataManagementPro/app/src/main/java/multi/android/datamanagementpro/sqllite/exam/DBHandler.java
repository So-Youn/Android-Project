package multi.android.datamanagementpro.sqllite.exam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class DBHandler {
    static EditText edtName;
    static EditText edtSu;
    static EditText edtPrice;
    static DBHandler handler;
    ListView listview;
    Context context;
    static SQLiteDatabase ExamDB;

    public DBHandler(Context context){
        this.context = context;
        ExamDBHelper helper = new ExamDBHelper(context);
        ExamDB = helper.getWritableDatabase();
    }

    public DBHandler open(Context context){
        if(context != null){
            handler = new DBHandler(context);
            return handler;
        }
        return handler;
    }

    public void insert(String edtName, String edtSu, String edtPrice) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",edtName);
        contentValues.put("price",edtSu);
        contentValues.put("su",edtPrice);
        contentValues.put("totPrice",Integer.parseInt(edtSu)*Integer.parseInt(edtPrice));
        ExamDB.insert("product",null,contentValues);
    }





    public ArrayList resultAll(String name, String price, String su) {
        ArrayList<String> data = new ArrayList<String>();
        StringBuffer sb = new StringBuffer();

        Cursor cursor = ExamDB.query("product",null,null,
                null,null,null,null);
        int count = cursor.getCount();
        while(cursor.moveToNext()){
            String id = cursor.getString(0);
            name = cursor.getString(1);
            price = cursor.getString(2);
            su = cursor.getString(3);
            sb.append(id + " ");
            sb.append(name + " ");
            sb.append(price+" ");
            sb.append("\n");
        }
        data.add(sb.toString()+"\n");
        return data;
    }

    public ArrayList resultTotal(String name, String price, String su) {
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ArrayList<String> list = new ArrayList<String>();
        Cursor cursor = ExamDB.query("product", null, null,
                null, null, null, null);
        int count = cursor.getCount();
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            name = cursor.getString(1);
            price = cursor.getString(2);
            su = cursor.getString(3);
            list.add(name);
            list.add(price);
            list.add(su);

            data.add(list);
        }
        return (ArrayList) cursor;
    }


    public static class resultTotal extends ArrayList {
    }
}
