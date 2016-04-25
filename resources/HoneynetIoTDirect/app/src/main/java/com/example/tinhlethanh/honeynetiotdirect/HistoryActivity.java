package com.example.tinhlethanh.honeynetiotdirect;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.tinhlethanh.honeynetiotdirect.Model.History;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    ArrayList<History> array_history = new ArrayList<History>();
    MyArrayAdapter adapter = null;
    DatabaseHelper databaseHelper = null;
    ListView lvHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        databaseHelper = new DatabaseHelper(this);
        lvHistory = (ListView) findViewById(R.id.listview);
        ImageButton btn_delete = (ImageButton) findViewById(R.id.btndelete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletehistoty();
            }
        });
        databaseHelper.open();
        array_history = databaseHelper.getListHistory();
        databaseHelper.close();
        adapter = new MyArrayAdapter(HistoryActivity.this, R.layout.history_acctivity, array_history);
        lvHistory.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    //ham khi click vao button xoa lich su
    public void deletehistoty(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Thiết lập tiêu đề hiển thị
        builder.setTitle("Thông báo");
        //Thiết lập thông báo hiển thị
        builder.setMessage("Bạn có muốn xóa lịch sử! ");

        //Tạo nút Hủy
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databaseHelper.open();
                databaseHelper.doDeleteDb();
                //cap nhap lai giao dien
                array_history = databaseHelper.getListHistory();
                databaseHelper.close();
                adapter = new MyArrayAdapter(HistoryActivity.this, R.layout.history_acctivity, array_history);
                lvHistory.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
}
