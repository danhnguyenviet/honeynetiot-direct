package com.example.tinhlethanh.honeynetiotdirect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tinh LE Thanh on 4/10/2016.
 */
public class DatabaseHelper{
    private static final String DATABASE_NAME = "DBHISTORY";

    /*Version database*/
    private static final int DATABASE_VERSION = 1;

    /*Tên tabel và các column trong database*/
    public static final String TABLE_HISTORY = "tbhistory";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOIDUNG = "history";
    public static final String COLUMN_DATE = "datetime";


    /*Các đối tượng khác*/
    private static Context context;
    static SQLiteDatabase db;
    private OpenHelper openHelper;

    /*Hàm dựng, khởi tạo đối tượng*/
    public DatabaseHelper(Context c) {
        DatabaseHelper.context = c;
    }

    /*Hàm mở kết nối tới database*/
    public DatabaseHelper open() throws SQLException {
        openHelper = new OpenHelper(context);
        db = openHelper.getWritableDatabase();
        return this;
    }

    /*Hàm đóng kết nối với database*/
    public void close() {
        openHelper.close();
    }

    /*Hàm doInsertRecord dùng để chèn dữ mới dữ liệu vào database*/
    public void doInsertRecord(History history)
    {
        ContentValues values=new ContentValues();
        values.put(COLUMN_NOIDUNG, history.getHistory());
        values.put(COLUMN_DATE, history.getDatetime());
        db.insert(TABLE_HISTORY, null, values);
    }
    /*Hàm getListHistory trả về toàn bộ dữ liệu của table*/
    public ArrayList<History> getListHistory() {
        ArrayList<History> historyList = new ArrayList<>();
        /////////////////
        Cursor cursor = db.query(TABLE_HISTORY,
                null, null, null, null, null, null);
        cursor.moveToFirst();
        while(cursor.isAfterLast()==false)
        {
            History history = new History();
            history.setId(Integer.parseInt(cursor.getString(0)));
            history.setHistory(cursor.getString(1));
            history.setDatetime(cursor.getString(2));
            historyList.add(history);
            cursor.moveToNext();
        }
        return historyList;
    }
    public void doDeleteDb()
    {
            if(db.delete(TABLE_HISTORY, null, null)!=0){
                Toast.makeText(DatabaseHelper.context, "Xóa lịch sử thành công !", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(DatabaseHelper.context, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
            }
    }

    //---------------- class OpenHelper ------------------
    private static class OpenHelper extends SQLiteOpenHelper {

        /*Hàm dựng khởi tạo 1 OpenHelper*/
        public OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /*Tạo mới database*/
        @Override
        public void onCreate(SQLiteDatabase arg0) {
            arg0.execSQL("CREATE TABLE " + TABLE_HISTORY + " ("
                    + COLUMN_ID + " integer primary key autoincrement, "
                    + COLUMN_NOIDUNG + " TEXT NOT NULL, "
                    + COLUMN_DATE + " TEXT NOT NULL);");

        }

        /*Kiểm tra phiên bản database nếu khác sẽ thay đổi*/
        @Override
        public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
            arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
            onCreate(arg0);
        }
    }
}
