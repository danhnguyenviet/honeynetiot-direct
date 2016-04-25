package com.example.tinhlethanh.honeynetiotdirect;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tinhlethanh.honeynetiotdirect.Model.History;

import java.util.ArrayList;


public class MyArrayAdapter extends ArrayAdapter<History>{
    Activity context=null;
    int layoutId;
    ArrayList<History>arr =null;
    public MyArrayAdapter(Activity context, int layoutId,
                          ArrayList<History> list){
        super(context, layoutId,list);
        this.context=context;
        this.layoutId=layoutId;
        this.arr=list;
    }
    @Override
    public View getView(int position, View convertView,ViewGroup parent){
        if(convertView==null){
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutId,null);
        }
        History history=arr.get(position);
        TextView ip=(TextView)convertView.findViewById(R.id.txtip);
        TextView port=(TextView)convertView.findViewById(R.id.txtport);
        TextView den=(TextView)convertView.findViewById(R.id.txtden);
        TextView date=(TextView)convertView.findViewById(R.id.txtdate);
        ip.setText("IP : "+history.getIp());
        port.setText("PORT : "+history.getPort());
        if(history.getDen()!=null)
            den.setText("Đèn : "+history.getDen());
        if(history.getBom()!=null)
            den.setText("Bơm : "+history.getBom());
        date.setText(history.getDatetime());
        return convertView;
    }
}
