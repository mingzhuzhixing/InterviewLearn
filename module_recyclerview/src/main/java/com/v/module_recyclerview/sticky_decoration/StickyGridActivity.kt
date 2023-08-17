package com.v.module_recyclerview.sticky_decoration

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.v.module_base.BaseTitleBarActivity
import com.v.module_recyclerview.R
import com.v.module_recyclerview.sticky_decoration.bean.City
import com.v.module_recyclerview.sticky_decoration.utils.CityUtil
import com.v.module_recyclerview.sticky_decoration_widget.StickyDecoration
import com.v.module_recyclerview.sticky_decoration_widget.listener.OnGroupClickListener
import com.v.module_recyclerview.sticky_decoration_widget.widget.MyRecyclerView
import com.v.module_utils.DensityUtils

/**
 * 文字悬浮
 */
class StickyGridActivity : BaseTitleBarActivity() {

    var dataList: MutableList<City> = mutableListOf()
    var mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null

    override fun setTitle() = "文字悬浮"

    override fun getLayoutId() = R.layout.activity_sticky_recycler_view


    override fun initData() {
        super.initData()

        //模拟数据
        dataList.addAll(CityUtil.getCityList())
        dataList.addAll(CityUtil.getCityList())

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val builder = StickyDecoration.Builder
                .init { position ->
                    //组名回调
                    if (dataList.size > position && position > -1) {
                        //获取组名，用于判断是否是同一组
                        dataList[position].province
                    } else null
                } //背景色
                .setGroupBackground(Color.parseColor("#48BDFF")) //高度
                .setGroupHeight(DensityUtils.dip2px(this, 35f)) //分割线颜色
                .setDivideColor(Color.parseColor("#EE96BC")) //分割线高度 (默认没有分割线)
                .setDivideHeight(DensityUtils.dip2px(this, 2f)) //字体颜色 （默认）
                .setGroupTextColor(Color.BLACK) //字体大小
                .setGroupTextSize(DensityUtils.sp2px(this, 15f)) // 边距   靠左时为左边距  靠右时为右边距
                .setTextSideMargin(DensityUtils.dip2px(this, 10f)) // header数量（默认0）
                //.setHeaderCount(1)
                //Group点击事件
                .setOnClickListener(object : OnGroupClickListener {
                    override fun onClick(position: Int, id: Int) {
                        //点击事件，返回当前分组下的第一个item的position
                        val content = "onGroupClick --> " + position + " " + dataList.get(position).getProvince()
                        Toast.makeText(this@StickyGridActivity, content, Toast.LENGTH_SHORT).show()
                    }
                })
        mAdapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
                return Holder(view)
            }

           override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
                val holder: Holder? = viewHolder as Holder?
               holder?.mTextView?.text = dataList[position].name
                holder?.itemView?.setOnClickListener {
                    Toast.makeText(this@StickyGridActivity, "item click $position", Toast.LENGTH_LONG).show()
                }
           }

            override fun getItemCount(): Int {
                return dataList.size
            }
        }

        val recyclerView: MyRecyclerView = findViewById<MyRecyclerView>(R.id.my_recycler_view)
        val layoutManager = GridLayoutManager(this, 3)
        builder.resetSpan(recyclerView, layoutManager)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(builder.build())
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextView: TextView

        init {
            mTextView = itemView.findViewById(R.id.tv)
        }
    }

    fun onAdd(view: View) {
        val previousSize = dataList.size
        val list = CityUtil.getCityList()
        dataList.addAll(list)
        mAdapter?.notifyItemRangeInserted(previousSize, list.size)
        mAdapter?.notifyItemRangeChanged(previousSize, list.size)
    }

    fun onClean(view: View) {
        dataList.clear()
        mAdapter?.notifyDataSetChanged()
    }

    fun onRefresh(view: View) {
        dataList.clear()
        dataList.addAll(CityUtil.getRandomCityList())
        mAdapter?.notifyDataSetChanged()
    }

    val position = 3
    fun onDelete(view: View) {
        dataList.remove(dataList[position])
        mAdapter?.notifyItemRemoved(position)
        mAdapter?.notifyItemRangeChanged(position, dataList.size - 3)
    }

    fun onDeleteLast(view: View) {
        val endPosition = dataList.size - 1
        dataList.removeAt(endPosition)
        mAdapter?.notifyItemRemoved(endPosition)
        mAdapter?.notifyItemChanged(endPosition)
    }
}
