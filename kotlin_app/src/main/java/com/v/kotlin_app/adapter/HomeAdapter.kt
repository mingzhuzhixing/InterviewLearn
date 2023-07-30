package com.v.kotlin_app.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.v.kotlin_app.R
import com.v.kotlin_app.bean.ItemDataBean

/**
 * ClassName: CollectAdapter
 * Description:
 * @package_name com.v.kotlin.adapter
 * @author zhuming
 * @date 2021/9/29 5:36 下午
 */
class HomeAdapter(private var mContext: Context) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    var mList: ArrayList<ItemDataBean> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setClearAndData(list: List<ItemDataBean>) {
        mList.clear()
        this.mList.addAll(list)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val itemView: View = inflater.inflate(R.layout.item_home, viewGroup, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        if (mList.isEmpty() && position < mList.size) {
            return
        }
        viewHolder.tvTitle.text = mList[position].title
        viewHolder.tvTime.text = mList[position].niceShareDate
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    //内部类
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvTime: TextView = itemView.findViewById(R.id.tv_time)
    }
}