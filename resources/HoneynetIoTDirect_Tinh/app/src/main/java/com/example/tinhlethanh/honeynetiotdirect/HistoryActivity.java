package com.example.tinhlethanh.honeynetiotdirect;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    ListView lvHistory = null;
    ArrayList<String>array_history = null;
    ArrayAdapter adapter = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        lvHistory = (ListView) findViewById(R.id.listview);
        ImageButton btn_delete = (ImageButton) findViewById(R.id.btndelete);
        array_history = new ArrayList<>();
        adapter = new ArrayAdapter<String>(HistoryActivity.this, android.R.layout.simple_list_item_1, array_history);
        lvHistory.setAdapter(adapter);
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard,"MyFolder/file.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                    array_history.add(line);
            }
            br.close();
        }
        catch (IOException e) {
        }
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletehistoty();
            }
        });
    }
    public void deletehistoty(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa lịch sử! ");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                File sdcard = Environment.getExternalStorageDirectory();
                File file = new File(sdcard, "MyFolder/file.txt");
                if (file.exists()) {
                    file.delete();
                    array_history.clear();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(HistoryActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HistoryActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
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
