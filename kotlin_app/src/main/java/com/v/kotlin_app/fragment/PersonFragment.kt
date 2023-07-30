package com.v.kotlin_app.fragment

import android.content.Intent
import com.v.kotlin_app.R
import com.v.kotlin_app.base.BaseFragment
import com.v.kotlin_app.basic_grammar.ForActivity
import com.v.kotlin_app.presenter.EmptyPresenter
import kotlinx.android.synthetic.main.fragment_person.*

/**
 * 个人fragment
 */
class PersonFragment : BaseFragment<EmptyPresenter>() {

    override fun getLayoutId() = R.layout.fragment_person

    override fun initData() {
    }

    override fun createPresenter(): EmptyPresenter? {
        return null
    }

    override fun processLogical() {
        btn_for.setOnClickListener {
            startActivity(Intent(activity, ForActivity::class.java))
        }
    }

}