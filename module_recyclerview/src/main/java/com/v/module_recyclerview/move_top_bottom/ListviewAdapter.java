package com.v.module_recyclerview.move_top_bottom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.v.module_recyclerview.R;

import java.util.List;

/**
 * listview 适配器
 */
public class ListviewAdapter extends BaseAdapter {
    List<String> dataList;
    private final LayoutInflater inflater;

    public ListviewAdapter(Context context, List<String> dataList) {
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_listview_ver, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String item = dataList.get(position);
        holder.textView.setText(item);
        return convertView;
    }

    private static class ViewHolder {
        TextView textView;
    }
}
