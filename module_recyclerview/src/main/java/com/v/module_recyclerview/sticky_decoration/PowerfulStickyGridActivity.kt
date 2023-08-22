package com.v.module_recyclerview.sticky_decoration

import android.graphics.Color
import android.util.Log
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
import com.v.module_recyclerview.sticky_decoration_widget.PowerfulStickyDecoration
import com.v.module_recyclerview.sticky_decoration.utils.CityUtil
import com.v.module_recyclerview.sticky_decoration_widget.listener.PowerGroupListener
import com.v.module_recyclerview.sticky_decoration_widget.widget.MyRecyclerView
import com.v.module_utils.DensityUtils
import kotlinx.android.synthetic.main.activity_sticky_recycler_view.*
import java.util.ArrayList

/**
 * 自定义View悬浮
 */
class PowerfulStickyGridActivity : BaseTitleBarActivity() {

    var mAdapter: RecyclerView.Adapter<*>? = null
    var dataList: MutableList<City> = ArrayList()
    var decoration: PowerfulStickyDecoration? = null

    override fun setTitle(): String {
        return "自定义View悬浮"
    }

    override fun getLayoutId(): Int {
       return R.layout.activity_sticky_recycler_view
    }

    override fun processLogical() {
        super.processLogical()
        btn_refresh.visibility = View.VISIBLE
        initView()
    }

    private fun initView() {
        val mRecyclerView: MyRecyclerView = findViewById(R.id.my_recycler_view)
        //模拟数据
        dataList.addAll(CityUtil.getCityList())
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
                .setOnClickListener { position, id -> //Group点击事件
                    val content = "onGroupClick --> " + dataList[position].province + "   id --> " + id
                    showToast(content)
                }
                .build()
        //-------------                  ----------------
        //下面是平时的RecyclerView操作
        mAdapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
                return Holder(view)
            }

            override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
                val holder = viewHolder as Holder?
                holder!!.mTextView.text = dataList[position].name
                holder.itemView.setOnClickListener { showToast("Item click $position") }
            }

            override fun getItemCount(): Int {
                return dataList.size
            }
        }
        val manager: GridLayoutManager = GridLayoutManager(this, 3)
        decoration?.resetSpan(mRecyclerView, manager)
        mRecyclerView.layoutManager = manager
        mRecyclerView.addItemDecoration(decoration!!)
        mRecyclerView.adapter = mAdapter
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextView: TextView

        init {
            mTextView = itemView.findViewById(R.id.tv)
        }
    }

    fun onRefresh(v: View?) {
        dataList.clear()
        dataList.addAll(CityUtil.getRandomCityList())
        mAdapter!!.notifyDataSetChanged()
        decoration!!.clearCache()
    }

    private fun showToast(content: String) {
        Toast.makeText(this@PowerfulStickyGridActivity, content, Toast.LENGTH_LONG).show()
    }

    private fun l(str: String) {
        Log.i("TAG", str)
    }
}