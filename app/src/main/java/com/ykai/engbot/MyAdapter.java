package com.ykai.engbot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

/**
 * Created by ykai on 2016/4/16.
 */


public class MyAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<String> mDatas;

    public MyAdapter(Context context, List<String> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item, null);
//            convertView = mInflater.inflate(R.layout.item, parent, false);
            holder.mBtn = (Button) convertView.findViewById(R.id.id_btn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.mBtn.setText(mDatas.get(position));

        return convertView;
    }

    private final class ViewHolder {
        Button mBtn;
    }
}