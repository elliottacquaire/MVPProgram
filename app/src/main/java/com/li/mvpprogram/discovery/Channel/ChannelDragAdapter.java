package com.li.mvpprogram.discovery.Channel;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.li.mvpprogram.R;
import com.li.mvpprogram.bean.MapVo;

import java.util.List;

/**
 */

public class ChannelDragAdapter extends DragAdapter {
    private List<MapVo> list ;
    private Context context;

    public ChannelDragAdapter(List<MapVo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public void onDataModelMove(int from, int to) {
        MapVo s = list.remove(from);
        list.add(to, s);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public MapVo getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            FrameLayout frameLayout = new FrameLayout(context);
            convertView = frameLayout;
            textView = new TextView(context);
            frameLayout.setPadding(20, 20, 20, 20);
            textView.setPadding(20, 20, 20, 20);

            if (position == 0){
                textView.setTextColor(ContextCompat.getColor(context,R.color.text999));
                textView.setBackgroundResource(R.drawable.channel_recommend_bg);

            }else {
                textView.setTextColor(ContextCompat.getColor(context,R.color.text333));
                textView.setBackgroundResource(R.drawable.channel_item_bg);
            }

            frameLayout.addView(textView);
            textView.setGravity(Gravity.CENTER);

        } else {
            textView = (TextView) ((FrameLayout) convertView).getChildAt(0);
        }
        textView.setText(getItem(position).getValue());
        return convertView;
    }
}
