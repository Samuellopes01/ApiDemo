package com.example.apidemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.apidemo.dto.itemModel;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<itemModel> mDataList;

    public CustomAdapter(Context context, ArrayList<itemModel> arrayList) {
        this.context = context;
        this.mDataList = arrayList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public  View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView ==  null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        }
        TextView Id, name, email, avt_url;

        Id = convertView.findViewById(R.id.Id);
        name =  convertView.findViewById(R.id.name);
        email =  convertView.findViewById(R.id.email);
        avt_url = convertView.findViewById(R.id.avt_url);

        Id.setText(mDataList.get(position).getId());
        name.setText(mDataList.get(position).getName());
        email.setText(mDataList.get(position).getEmail());
        avt_url.setText(mDataList.get(position).getAvt_url());

        return convertView;
    }
}