package multi.android.datamanagementpro.sqllite.exam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class ExamDBHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;

    public ExamDBHelper(Context context) {
        super(context,"product.db",null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("dbtest","데이터베이스가 생성되었습니다.");
        String sql = "create table if not exists product("
                + "_id integer primary key autoincrement,"
                + "name text not null, "
                + "price integer not null, "
                + "su integer not null, "
                + "totPrice integer not null)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("dbtest","데이터베이스의 스키마가 변경되었습니다 : oldVersion :"
                +oldVersion+"newVersion:"+newVersion);
        switch (oldVersion){
            case 1:
                Log.d("dbtest","2버전으로 변경");
            case 2:
            case 3:


        }
    }
}
