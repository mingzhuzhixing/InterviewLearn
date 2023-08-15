package com.v.module_recyclerview.ceiling

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.v.module_base.BaseTitleBarActivity
import com.v.module_recyclerview.R

/**
 * 吸顶效果
 */
class CeilingActivity : BaseTitleBarActivity() {

    private val beanList = mutableListOf<Bean>()
    private var recyclerView: RecyclerView? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_ceiling
    }

    override fun setTitle(): String {
        return "吸顶效果"
    }

    override fun processLogical() {
        super.processLogical()

        for (i in 0..5) {
            beanList.add(Bean("第一组 ${i + 1} 号", "第一组"))
        }
        for (i in 0..5) {
            beanList.add(Bean("第二组 ${i + 1} 号", "第二组"))
        }
        for (i in 0..5) {
            beanList.add(Bean("第三组 ${i + 1} 号", "第三组"))
        }
        for (i in 0..5) {
            beanList.add(Bean("第四组 ${i + 1} 号", "第四组"))
        }
        for (i in 0..5) {
            beanList.add(Bean("第五组 ${i + 1} 号", "第五组"))
        }
        for (i in 0..5) {
            beanList.add(Bean("第六组 ${i + 1} 号", "第六组"))
        }
        for (i in 0..5) {
            beanList.add(Bean("第七组 ${i + 1} 号", "第七组"))
        }


        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.adapter = SampleAdapter2(beanList)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.layoutParams?.width = ViewGroup.LayoutParams.MATCH_PARENT
        recyclerView?.layoutParams?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        recyclerView?.addItemDecoration(StickyItemDecoration(this, beanList))
    }


    class SampleAdapter2(var data: MutableList<Bean>? = mutableListOf()) :
        RecyclerView.Adapter<Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
        }

        override fun getItemCount(): Int {
            return data?.size ?: 0
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bindData(data?.get(position)?.text)
        }
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private var textView: TextView? = null

        init {
            textView = view.findViewById(R.id.tv)
        }

        fun bindData(s: String?) {
            s ?: return
            textView?.text = s
        }
    }
}