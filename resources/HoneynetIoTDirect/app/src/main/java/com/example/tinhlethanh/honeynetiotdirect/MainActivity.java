package com.example.tinhlethanh.honeynetiotdirect;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tinhlethanh.honeynetiotdirect.Model.History;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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

    /**
     * Check the Internet connection. Return true if phone has connection.
     * Retrun false if phone hasn't connection
     * @return
     */
    private boolean checkInternetConenction() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||

                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;
        }else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
            Toast.makeText(this, " Not Connected ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return false;
    }

    /**
     * Open a Http connection
     * @param urlStr url string is used to open a Http connection
     * @return
     */
    private InputStream openHttpConnection(String urlStr) {
        InputStream in = null;
        int resCode = -1;

        try {
            URL url = new URL(urlStr);
            URLConnection urlConn = url.openConnection();

            if (!(urlConn instanceof HttpURLConnection)) {
                throw new IOException("URL is not an Http URL");
            }
            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            httpConn.setAllowUserInteraction(false);
            httpConn.setInstanceFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            resCode = httpConn.getResponseCode();

            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
        }

        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        return in;
    }

    /**
     * Send a GET request to server
     * @param urlStr url string is used to make GET request
     */
    private void sendGETRequest(String urlStr) {
        final String url = urlStr;

        new Thread() {
            public void run() {
                InputStream in = null;
                try {
                    in = openHttpConnection(url);
                    if (!in.equals(null)) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                        String line = "", content = "";
                        while ((line = bufferedReader.readLine()) != null) {
                            content += line;
                        }

                        if (content == "")
                            System.out.println("Test 1 - null");
                    }
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }.start();

    }
}
