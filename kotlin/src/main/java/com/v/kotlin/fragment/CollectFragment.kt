package com.v.kotlin.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.v.kotlin.R
import com.v.kotlin.adapter.CollectAdapter
import com.v.kotlin.base.BaseFragment
import com.v.kotlin.bean.Student
import com.v.kotlin.ivew.ICollectView
import com.v.kotlin.presenter.CollectPresenterImpl
import com.v.module_recyclerview.decoration.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_collect.*

/**
 * 收藏
 */
class CollectFragment : BaseFragment<CollectPresenterImpl>(), ICollectView {

    private lateinit var mAdapter: CollectAdapter

    override fun getLayoutId() = R.layout.fragment_collect

    override fun initData() {
        mAdapter = CollectAdapter(mActivity)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
            addItemDecoration(
                HorizontalDividerItemDecoration.Builder(mActivity)
                    .colorResId(R.color.teal_200)
                    .sizeResId(R.dimen.dp_1_half)
                    .build()
            )
        }
    }

    override fun createPresenter(): CollectPresenterImpl = CollectPresenterImpl(this)

    override fun processLogical() {
        mPresenter?.requestQueryAll()

        fab_add.setOnClickListener {
            mPresenter?.requestInsert(Student("张三"), Student("李四", 25))
            mPresenter?.requestQueryAll()
        }

        fab_delete.setOnClickListener {
            mPresenter?.deleteAllStudent()
        }
    }

    override fun showAllStudent(data: List<Student>) {
        mAdapter.setClearAndData(data)
    }

    override fun deleteAllStudent() {
        mAdapter.setClearAndData(listOf())
    }
}