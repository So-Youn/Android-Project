package multi.android.datamanagementpro.sqllite.exam;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class DBHandler {
    static ExamDBHelper examDBHelper;
    static SQLiteDatabase db;
    Context context;
    public DBHandler(Context context){
        this.context = context;
        examDBHelper = new ExamDBHelper(context);
        db = examDBHelper.getWritableDatabase();
    }
    public void insert(String edtName, String edtSu, String edtPrice){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", edtName);
        contentValues.put("price", Integer.parseInt(edtPrice));
        contentValues.put("su", Integer.parseInt(edtSu));
        contentValues.put("totPrice",
                (Integer.parseInt(edtSu)
                        *Integer.parseInt(edtPrice)));
        db.insert("product",null,contentValues);
    }
    public Cursor result1(){
        Cursor cursor = db.query("product",
                new String[]{"_id", "name", "price"},
                null,null,
                null,null,null);
        return cursor;
    }
    public Cursor result2(){
        Cursor cursor = db.query("product",
                null,null,null,
                null,null,null);
        return cursor;
    }
    public Cursor search(String edtName){
        String where = "name like ?";
        String[] whereVal = {"%"+edtName+"%"};
        Cursor cursor = db.query("product",new String[]{"name","price"},where,whereVal,
                null,null,null);
        return cursor;
    }
    public Cursor detail(int position){
        String where = "_id = ?";
        StringBuffer sb = new StringBuffer();
        sb.append(position+1);
        String[] whereVal = {sb.toString()};
        Cursor cursor = db.query("product",null,where,
                whereVal,null, null,null);

        return cursor;
    }
}