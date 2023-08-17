package com.v.module_recyclerview.sticky_decoration

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.v.module_recyclerview.R
import com.v.module_recyclerview.sticky_decoration.adapter.SimpleAdapter
import com.v.module_recyclerview.sticky_decoration.bean.City
import com.v.module_recyclerview.sticky_decoration_widget.PowerfulStickyDecoration
import com.v.module_recyclerview.sticky_decoration.utils.CityUtil
import com.v.module_recyclerview.sticky_decoration_widget.listener.PowerGroupListener
import com.v.module_recyclerview.sticky_decoration_widget.widget.MyRecyclerView
import com.v.module_utils.DensityUtils
import java.util.ArrayList

/**
 * 可展开的recyclerview View悬浮
 */
class ExpandableActivity : AppCompatActivity() {
    var mAdapter: RecyclerView.Adapter<*>? = null
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

        //------------- PowerfulStickyDecoration 使用部分  ----------------
        decoration = PowerfulStickyDecoration.Builder
                .init(object : PowerGroupListener {
                    override fun getGroupName(position: Int): String? {
                        //获取组名，用于判断是否是同一组
                        return if (dataList.size > position) {
                            dataList[position].province
                        } else null
                    }

                    override fun getGroupView(position: Int): View? {
                        //获取自定定义的组View
                        return if (dataList.size > position) {
                            val view = layoutInflater.inflate(R.layout.city_group, null, false)
                            (view.findViewById<View>(R.id.tv) as TextView).text = dataList[position].province
                            val imageView = view.findViewById<View>(R.id.iv) as ImageView
                            imageView.setImageResource(dataList[position].icon)
                            view
                        } else {
                            null
                        }
                    }
                })
                .setCacheEnable(true)
                .setGroupHeight(DensityUtils.dip2px(this@ExpandableActivity, 40f))
                .setOnClickListener { position, id ->
                    if (dataList.size > position) {
                        //修改数据
                        changeExpandedState(position)
                        val city = dataList[position]
                        //修改悬浮窗
                        val view = layoutInflater.inflate(R.layout.city_group, null, false)
                        (view.findViewById<View>(R.id.tv) as TextView).text = dataList[position].province
                        val imageView = view.findViewById<View>(R.id.iv) as ImageView
                        imageView.setImageResource(dataList[position].icon)
                        val ivExpanded = view.findViewById<View>(R.id.iv_expanded) as ImageView
                        val rotation = if (city.isExpanded) 0 else 180
                        ivExpanded.rotation = rotation.toFloat()
                        //修改数据后，刷新指定的悬浮窗
                        decoration!!.notifyRedraw(mRecyclerView, view, position)
                        mAdapter!!.notifyDataSetChanged()
                    }
                }
                .build()
        //----------------                 -------------
        //下面是平时的RecyclerView操作

        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.layoutManager = manager
        mRecyclerView.addItemDecoration(decoration!!)
        mAdapter = SimpleAdapter(this, dataList)
        mRecyclerView.adapter = mAdapter
    }

    /**
     * 修改数据
     *
     * @param position
     */
    private fun changeExpandedState(position: Int) {
        var position = position
        if (dataList.size > position) {
            val city = dataList[position]
            city.isExpanded = !city.isExpanded
            position++
            if (dataList.size > position) {
                //下个是当前分组
                val city2 = dataList[position]
                if (TextUtils.equals(city.province, city2.province)) {
                    changeExpandedState(position)
                }
            }
        }
    }
}