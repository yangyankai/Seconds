package com.ykai.engbot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ykai.engbot.R;
import com.ykai.engbot.entity.RecordEntity;
import com.ykai.engbot.util.TimeUtil;
import com.ykai.englishdialog.myapplication.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ykai on 17/11/25.
 */
public class RecordAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<RecordEntity> mDatas;

    public RecordAdapter(Context context, ArrayList<RecordEntity> datas) {
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

        convertView = mInflater.inflate(R.layout.record_list_item, null);

        TextView textWeek = (TextView) convertView.findViewById(R.id.week);
        TextView textHour = (TextView) convertView.findViewById(R.id.hour);
        TextView textSumary = (TextView) convertView.findViewById(R.id.summary);
        TextView textTitle = (TextView) convertView.findViewById(R.id.history);

        String begin = mDatas.get((int) getItemId(position)).beginTimeStamp;
        String end = mDatas.get((int) getItemId(position)).endTimeStamp;

        textWeek.setText(TimeUtil.getWeekOfDate(begin) + " " + TimeUtil.getMonthAndDay(begin));
        textTitle.setText("会议记录" + (position + 1));
        textHour.setText(TimeUtil.getMinAndSeconds(begin) + "-" + TimeUtil.getMinAndSeconds(end) + "," + TimeUtil.getDuration(begin, end));
        textSumary.setText("summary");

        return convertView;

    }
}
