package com.v.mvp_module.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.v.mvp_module.R;
import com.v.mvp_module.bean.Girl;

import java.util.List;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-05 15:44
 */
public class GirlAdapter extends BaseAdapter {
    Context mContext;
    List<Girl> girls;
    ViewHolder mHolder;

    public GirlAdapter(Context mContext, List<Girl> girls){
        this.mContext=mContext;
        this.girls=girls;
    }

    @Override
    public int getCount() {
        return girls.size();
    }

    @Override
    public Object getItem(int i) {
        return girls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.activity_mvp_item, null);
            mHolder=new ViewHolder();
            mHolder.tvName=view.findViewById(R.id.tv_name);
            mHolder.tvDesc=view.findViewById(R.id.tv_desc);
            view.setTag(mHolder);
        }else{
            mHolder= (ViewHolder) view.getTag();
        }

        mHolder.tvName.setText(girls.get(i).getName());
        mHolder.tvDesc.setText(girls.get(i).getDesc());
        return view;
    }

    static class ViewHolder{
        TextView tvName;
        TextView tvDesc;
    }
}
