package com.v.kotlin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
class CollectAdapter(private var mContext: Context) : RecyclerView.Adapter<CollectAdapter.MyViewHolder>() {
    var mList: ArrayList<Student> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setClearAndData(list: List<Student>) {
        mList.clear()
        this.mList.addAll(list)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): MyViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val itemView: View = inflater.inflate(R.layout.item_collect, viewGroup, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        if (mList.isEmpty() && position < mList.size) {
            return
        }
        viewHolder.tvNo.text = mList[position].id.toString()
        viewHolder.tvName.text = mList[position].stuName
        viewHolder.tvAge.text = mList[position].age.toString()
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    //内部类
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNo: TextView = itemView.findViewById(R.id.tv_no)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvAge: TextView = itemView.findViewById(R.id.tv_age)
    }
}