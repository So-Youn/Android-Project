package multi.android.datamanagementpro.sqllite.exam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class DBHandler {

    static DBHandler handler;
    static ExamDBHelper helper; // 자기 자신을 매개변수로
    Context context;
    static SQLiteDatabase ExamDB;

    public DBHandler(Context context){
        this.context = context;
        helper = new ExamDBHelper(context);
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





    public Cursor resultAll() {
      Cursor cursor = ExamDB.query("product",new String[]{"id","name","price"},null,null,null,null,null);
        ArrayList<String> list = new ArrayList<String>(); //ArrayList형태의 변수 선언
        StringBuffer sb = new StringBuffer();
      while(cursor.moveToNext()){
          int idx = cursor.getInt(0);
          String name = cursor.getString(1);
          int price = cursor.getInt(2);
          sb.append(idx + ",").append(name+",").append(price+",");
          list.add(sb.toString());
      }

      return (Cursor) list;
    }

    public Cursor resultTotal() {
      Cursor cursor = ExamDB.query("product",null,null,null,null,
              null,null);

        return cursor;
    }


}
