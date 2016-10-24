package com.mu.compet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Mu on 2016-10-24.
 */

public class MyPageGridAdapter extends BaseAdapter {

    Context context;
    int layout;
    int img[];
    LayoutInflater inflater;

    public MyPageGridAdapter(Context context, int layout, int[] img) {
        this.context = context;
        this.layout = layout;
        this.img = img;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public Object getItem(int position) {
        return img[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
            convertView = inflater.inflate(layout, null);
        ImageView iv = (ImageView)convertView.findViewById(R.id.image);
        iv.setImageResource(img[position]);

        return convertView;
    }
}
