package com.example.tinhlethanh.honeynetiotdirect;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btn_send1, btn_send2, btn_delete;
    EditText edt_ip, edt_port, edt_den, edt_bom;
    ListView lvHistory;
    ArrayList<History> array_history = new ArrayList<History>();
    MyArrayAdapter adapter = null;
    DatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_send1 = (Button) findViewById(R.id.button1);
        btn_send2 = (Button) findViewById(R.id.button2);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        edt_ip = (EditText) findViewById(R.id.editText1);
        edt_port = (EditText) findViewById(R.id.editText4);
        edt_den = (EditText) findViewById(R.id.editText2);
        edt_bom = (EditText) findViewById(R.id.editText5);
        lvHistory = (ListView) findViewById(R.id.listview);
        databaseHelper = new DatabaseHelper(this);
        btn_send1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myclick();
            }
        });
        btn_send2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myclick();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadhistoty();
            }
        });
        databaseHelper.open();
        array_history = databaseHelper.getListHistory();
        databaseHelper.close();
        adapter = new MyArrayAdapter(MainActivity.this, R.layout.history_acctivity, array_history);
        lvHistory.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    //ham khi click vao button
    public void myclick(){
        String s = "IP : "+edt_ip.getText().toString() + " - PORT : " + edt_port.getText().toString() + " \n"+"Đèn : " + edt_den.getText().toString() + " - Bơm : " + edt_bom.getText().toString() + "";
        Date date = new Date();
        String datetime = date.toString();
        History history = new History(datetime, s);
        databaseHelper.open();
        databaseHelper.doInsertRecord(history);
        //cap nhap lai giao dien
        array_history = databaseHelper.getListHistory();
        databaseHelper.close();
        adapter = new MyArrayAdapter(MainActivity.this, R.layout.history_acctivity, array_history);
        lvHistory.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    //ham khi click vao button xoa lich su
    public void loadhistoty(){

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
                adapter = new MyArrayAdapter(MainActivity.this, R.layout.history_acctivity, array_history);
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
