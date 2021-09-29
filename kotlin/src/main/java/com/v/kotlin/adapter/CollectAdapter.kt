package com.v.kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.v.kotlin.R
import com.v.kotlin.bean.Student

/**
 * ClassName: CollectAdapter
 * Description:
 * @package_name com.v.kotlin.adapter
 * @author zhuming
 * @date 2021/9/29 5:36 下午
 */
class CollectAdapter(private var mContext: Context) :
    RecyclerView.Adapter<CollectAdapter.MyViewHolder>() {
    var mList: List<Student>? = null

    fun setCleanData(list: List<Student>) {
        this.mList = list
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): MyViewHolder {
        val itemView: View =
            LayoutInflater.from(mContext).inflate(R.layout.item_collect, viewGroup, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        if (mList == null || mList?.isEmpty()!!) {
            return
        }
        viewHolder.tvNo.text = mList?.get(position)?.id?.toString()
        viewHolder.tvName.text = mList?.get(position)?.stuName
        viewHolder.tvAge.text = mList?.get(position)?.age?.toString()
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNo: TextView = itemView.findViewById(R.id.tv_no)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvAge: TextView = itemView.findViewById(R.id.tv_age)
    }
}