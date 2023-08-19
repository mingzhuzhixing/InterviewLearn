package com.v.module_recyclerview.sticky_decoration

import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.v.module_base.BaseTitleBarActivity
import com.v.module_recyclerview.R
import com.v.module_recyclerview.sticky_decoration.bean.City
import com.v.module_recyclerview.sticky_decoration.utils.CityUtil
import com.v.module_recyclerview.sticky_decoration_widget.StickyDecoration
import com.v.module_recyclerview.sticky_decoration_widget.listener.OnGroupClickListener
import com.v.module_recyclerview.sticky_decoration_widget.widget.MyRecyclerView
import com.v.module_utils.DensityUtils
import kotlinx.android.synthetic.main.activity_sticky_recycler_view.*

/**
 * 文字悬浮
 */
class StickyActivity : BaseTitleBarActivity() {

    var dataList: MutableList<City> = mutableListOf()
    var mAdapter: QuickAdapter? = null;

    override fun setTitle() = "文字悬浮"

    override fun getLayoutId() = R.layout.activity_sticky_recycler_view


    override fun initData() {
        super.initData()
        btn_add.visibility = View.VISIBLE
        btn_clean.visibility = View.VISIBLE
        btn_refresh.visibility = View.VISIBLE
        btn_delete.visibility = View.VISIBLE
        btn_delete_list.visibility = View.VISIBLE

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
                        Toast.makeText(this@StickyActivity, content, Toast.LENGTH_SHORT).show()
                    }
                })
        //.setSticky(false)

        mAdapter = QuickAdapter()
        mAdapter?.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                val city = dataList[position]
                Toast.makeText(this@StickyActivity,
                        "item click " + position + " : " + city.province + " - " + city.name, Toast.LENGTH_SHORT).show()
            }
        })

        val recyclerView: MyRecyclerView = findViewById<MyRecyclerView>(R.id.my_recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@StickyActivity, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
            addItemDecoration(builder.build())
        }

        mAdapter?.setNewData(dataList)
    }

    class QuickAdapter : BaseQuickAdapter<City, BaseViewHolder>(R.layout.item_recycler_view) {

        override fun convert(holder: BaseViewHolder, item: City) {
            holder.setText(R.id.tv, item.name)
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
