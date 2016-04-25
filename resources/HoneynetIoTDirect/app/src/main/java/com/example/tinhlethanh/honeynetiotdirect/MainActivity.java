package com.example.tinhlethanh.honeynetiotdirect;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tinhlethanh.honeynetiotdirect.Model.History;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btn_send1, btn_send2;
    EditText edt_ip, edt_port, edt_den, edt_bom;


    DatabaseHelper databaseHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_send1 = (Button) findViewById(R.id.button1);
        btn_send2 = (Button) findViewById(R.id.button2);

        edt_ip = (EditText) findViewById(R.id.editText1);
        edt_port = (EditText) findViewById(R.id.editText4);
        edt_den = (EditText) findViewById(R.id.editText2);
        edt_bom = (EditText) findViewById(R.id.editText5);

        databaseHelper = new DatabaseHelper(this);
        btn_send1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_ip.getText().toString().equals("") == true  || edt_port.getText().toString().equals("") == true || edt_den.getText().toString().equals("") == true) {
                    DialogLoi();
                }else {
                    Date today = Calendar.getInstance().getTime();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                    String datetime = formatter.format(today);
                    History history = new History(edt_ip.getText().toString(), edt_port.getText().toString(), edt_den.getText().toString(), null, datetime);
                    databaseHelper.open();
                    databaseHelper.doInsertRecord(history);
                    databaseHelper.close();
                    Toast.makeText(MainActivity.this, "Gửi thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_send2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_ip.getText().toString().equals("") == true  || edt_port.getText().toString().equals("") == true || edt_bom.getText().toString().equals("") == true) {
                    DialogLoi();
                }else {
                    Date today = Calendar.getInstance().getTime();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                    String datetime = formatter.format(today);
                    History history = new History(edt_ip.getText().toString(), edt_port.getText().toString(), null, edt_bom.getText().toString(), datetime);
                    databaseHelper.open();
                    databaseHelper.doInsertRecord(history);
                    databaseHelper.close();
                    Toast.makeText(MainActivity.this, "Gửi thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void DialogLoi(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Thiết lập tiêu đề hiển thị
        builder.setTitle("Lỗi");
        //Thiết lập thông báo hiển thị
        builder.setMessage("Bạn cần phải nhập dữ liệu vào! ");
        builder.setCancelable(false);
        //Tạo nút Hủy
        builder.setNeutralButton("OK", null);
        builder.create().show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btnHistory) {
            //đến màng hình HistoryActivity
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivityForResult(intent, 0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
