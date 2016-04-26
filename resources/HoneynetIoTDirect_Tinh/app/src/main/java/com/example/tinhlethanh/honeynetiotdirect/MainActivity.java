package com.example.tinhlethanh.honeynetiotdirect;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tinhlethanh.honeynetiotdirect.Model.HistoryBom;
import com.example.tinhlethanh.honeynetiotdirect.Model.HistoryDen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    Button btn_send1, btn_send2;
    EditText edt_ip, edt_port, edt_den, edt_bom;
    String fileName = "file.txt";
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
        btn_send1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                den();
            }
        });
        btn_send2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bom();
            }
        });
    }
    //ham myclick cua cac button
    public void den(){
    if (edt_ip.getText().toString().isEmpty()||edt_port.getText().toString().isEmpty()||edt_den.getText().toString().isEmpty()) {
            DialogLoi();
        } else {
            Date today = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
            String datetime = formatter.format(today);
            HistoryDen historyDen = new HistoryDen(edt_ip.getText().toString(), edt_port.getText().toString(), edt_den.getText().toString(), datetime);
        try
        {
            File root = new File(Environment.getExternalStorageDirectory()+File.separator+"MyFolder");
            if (!root.exists())
            {
                root.mkdirs();
            }
            File gpxfile = new File(root, fileName);
            FileWriter writer = new FileWriter(gpxfile,true);
            writer.append(historyDen.toString()+"\n");
            writer.flush();
            writer.close();
            Toast.makeText(this, "Thêm dữ liệu thành công", Toast.LENGTH_SHORT).show();
        }
        catch(IOException e)
        {
            Toast.makeText(this, "Thêm dữ liệu thất bại", Toast.LENGTH_SHORT).show();
        }
        }
    }
    public void bom(){
        if (edt_ip.getText().toString().isEmpty()||edt_port.getText().toString().isEmpty()||edt_bom.getText().toString().isEmpty()) {
            DialogLoi();
        } else {
            Date today = Calendar.getInstance().getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
            String datetime = formatter.format(today);
            HistoryBom historyBom = new HistoryBom(edt_ip.getText().toString(), edt_port.getText().toString(), edt_den.getText().toString(), datetime);
            try
            {
                File root = new File(Environment.getExternalStorageDirectory()+File.separator+"MyFolder");
                if (!root.exists())
                {
                    root.mkdirs();
                }
                File gpxfile = new File(root, fileName);
                FileWriter writer = new FileWriter(gpxfile,true);
                writer.append(historyBom.toString()+"\n");
                writer.flush();
                writer.close();
                Toast.makeText(this, "Thêm dữ liệu thành công", Toast.LENGTH_SHORT).show();
            }
            catch(IOException e)
            {
                Toast.makeText(this, "Thêm dữ liệu thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //dialog hien thi khiem tra rang buoc loi
    public void DialogLoi(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Lỗi");
        builder.setMessage("Bạn cần phải nhập dữ liệu vào! ");
        builder.setCancelable(false);
        builder.setNeutralButton("OK", null);
        builder.create().show();
    }

    //phan hien thi menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btnHistory) {
            Intent intent = new Intent(this, HistoryActivity.class);
            startActivityForResult(intent, 0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
