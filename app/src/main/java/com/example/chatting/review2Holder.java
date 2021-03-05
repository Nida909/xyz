package com.example.chatting;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class review2Holder extends BaseAdapter {
    private Activity context;
    ArrayList<review2> Rv;


    public review2Holder(Activity context, ArrayList cust) {
        // super(context, R.layout.row_item, countries);
        this.context = context;
        this.Rv=cust;

    }

    public static class ViewHolder
    {
        TextView textView;
        TextView textView2;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;

        LayoutInflater inflater = context.getLayoutInflater();
               ViewHolder vh;
        if(convertView==null) {
            vh=new ViewHolder();
            row = inflater.inflate(R.layout.reviewstyle2, null, true);
            vh.textView = (TextView) row.findViewById(R.id.text);
            vh.textView2 = (TextView) row.findViewById(R.id.text2);
            // store the holder with the view.
            row.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.textView2.setText(Rv.get(position).getReview());
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
