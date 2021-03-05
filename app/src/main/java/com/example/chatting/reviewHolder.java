package com.example.chatting;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class reviewHolder extends BaseAdapter {
    private Activity context;
    ArrayList<ReviewList> Rv;


    public reviewHolder(Activity context, ArrayList cust) {
        // super(context, R.layout.row_item, countries);
        this.context = context;
        this.Rv=cust;

    }

    public static class ViewHolder
    {
        TextView textView;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;

        LayoutInflater inflater = context.getLayoutInflater();
       ViewHolder vh;
        if(convertView==null) {
            vh=new ViewHolder();
            row = inflater.inflate(R.layout.reviewstyle, null, true);
            vh.textView = (TextView) row.findViewById(R.id.txt);

            // store the holder with the view.
            row.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.textView.setText(Rv.get(position).getName());

        return  row;
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return position;
    }

    public int getCount() {

        if(Rv.size()<=0)
            return 1;
        return Rv.size();
    }
}
