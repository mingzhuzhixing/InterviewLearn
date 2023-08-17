package com.v.module_recyclerview.sticky_decoration

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.v.module_recyclerview.sticky_decoration.bean.City
import com.v.module_recyclerview.sticky_decoration_widget.PowerfulStickyDecoration
import com.v.module_recyclerview.sticky_decoration.utils.CityUtil
import com.v.module_recyclerview.sticky_decoration_widget.listener.PowerGroupListener
import com.v.module_utils.DensityUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.v.module_recyclerview.R
import com.v.module_recyclerview.sticky_decoration_widget.widget.MyRecyclerView
import java.util.ArrayList

/**
 * 自定义View悬浮
 */
class PowerfulStickyActivity : AppCompatActivity() {

    var mAdapter: QuickAdapter? = null
    var dataList: MutableList<City> = ArrayList()
    var decoration: PowerfulStickyDecoration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sticky_recycler_view)
        initView()
    }

    private fun initView() {
        val mRecyclerView: MyRecyclerView = findViewById(R.id.my_recycler_view)
        //模拟数据
        dataList.addAll(CityUtil.getCityList())
        dataList.addAll(CityUtil.getCityList())

        //------------- PowerfulStickyDecoration 使用部分  ----------------
        val listener: PowerGroupListener = object : PowerGroupListener {
            override fun getGroupName(position: Int): String? {
                //获取组名，用于判断是否是同一组
                return if (dataList.size > position) {
                    dataList[position].province
                } else null
            }

            override fun getGroupView(position: Int): View? {
                //获取自定定义的组View
                return if (dataList.size > position) {
                    val view = layoutInflater.inflate(R.layout.item_group, null, false)
                    (view.findViewById<View>(R.id.tv) as TextView).text = dataList[position].province
                    view
                } else {
                    null
                }
            }
        }
        decoration = PowerfulStickyDecoration.Builder
                .init(listener)
                .setGroupHeight(DensityUtils.dip2px(this, 40f))
                .setGroupBackground(Color.parseColor("#48BDFF"))
                .setDivideColor(Color.parseColor("#27ad9a"))
                .setDivideHeight(DensityUtils.dip2px(this, 1f))
                .setCacheEnable(true)
                .setHeaderCount(1)
                .setOnClickListener { position, id -> //Group点击事件
                    val content = "onGroupClick --> " + dataList[position].province + "   id --> " + id
                    showToast(content)
                }
                .build()
        //-------------                  ----------------
        //下面是平时的RecyclerView操作
        mAdapter = QuickAdapter()
        mAdapter?.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
                val city = dataList[position]
                Toast.makeText(this@PowerfulStickyActivity,
                        "item click " + position + " : " + city.province + " - " + city.name,
                        Toast.LENGTH_LONG).show()
            }
        })
        // header
        val header = TextView(this)
        header.text = "header"
        header.gravity = Gravity.CENTER
        header.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200)
        mAdapter?.addHeaderView(header)

        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.addItemDecoration(decoration!!)
        mRecyclerView.adapter = mAdapter
    }

    class QuickAdapter : BaseQuickAdapter<City, BaseViewHolder>(R.layout.item_recycler_view) {

        override fun convert(holder: BaseViewHolder, item: City) {
            holder.setText(R.id.tv, item.name)
        }
    }

    fun onRefresh(v: View?) {
        dataList.clear()
        dataList.addAll(CityUtil.getRandomCityList())
        mAdapter?.notifyDataSetChanged()
        decoration?.clearCache()
    }

    private fun showToast(content: String) {
        Toast.makeText(this@PowerfulStickyActivity, content, Toast.LENGTH_LONG).show()
    }

    private fun l(str: String) {
        Log.i("TAG", str)
    }
}