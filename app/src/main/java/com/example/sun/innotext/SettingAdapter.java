package com.example.sun.innotext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sun on 2018/2/25.
 */

public class SettingAdapter extends BaseAdapter {
    private List<SettingItem> data;
    private LayoutInflater layoutInflater;
    private Context context;
    public SettingAdapter(Context context,List<SettingItem> data) {
        this.context=context;
        this.layoutInflater=LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SettingItem item=(SettingItem)getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view=layoutInflater.inflate(R.layout.list_item,null);
            viewHolder=new ViewHolder();
            viewHolder.icon= view.findViewById(R.id.setting_icon);
            viewHolder.name= view.findViewById(R.id.setting_name);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.icon.setImageResource(item.getImageId());
        viewHolder.name.setText(item.getSettingName());

        return view;
    }
    class ViewHolder{
        ImageView icon;
        TextView name;
    }
}
