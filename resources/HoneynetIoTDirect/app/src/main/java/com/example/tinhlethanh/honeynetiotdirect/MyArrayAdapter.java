package com.example.tinhlethanh.honeynetiotdirect;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        TextView kq=(TextView)convertView.findViewById(R.id.txthistory);
        TextView date=(TextView)convertView.findViewById(R.id.txtdate);
        kq.setText(history.getHistory());
        date.setText(history.getDatetime());
        return convertView;
    }
}
