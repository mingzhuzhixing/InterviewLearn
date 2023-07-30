package com.v.kotlin_app.presenter

import com.v.kotlin_app.base.IBasePresenter
import com.v.kotlin_app.bean.Student
import com.v.kotlin_app.ivew.ICollectView
import com.v.kotlin_app.model.ICollectionModelImpl

/**
 * ClassName: CollectPresenter
 * Description:
 * @package_name com.v.kotlin_app.presenter
 * @author zhuming
 * @date 2021/9/28 4:38 下午
 */
interface ICollectPresenter : IBasePresenter {
    fun requestInsert(vararg student: Student)
    fun requestUpdate(vararg student: Student)
    fun requestDelete(vararg student: Student)

    /**
     * 获取全部
     */
    fun requestQueryAll()

    /**
     * 删除全部
     */
    fun requestDeleteAll()


    interface OnCollectLister {

        fun showAllStudent(students: List<Student>?)

        fun deleteAllStudent()
    }
}

class CollectPresenterImpl(var collectView: ICollectView?) : ICollectPresenter, ICollectPresenter.OnCollectLister {

    val collectModel = ICollectionModelImpl()

    /**
     * 插入
     */
    override fun requestInsert(vararg student: Student) {
        collectModel.requestInsert(this, *student)
    }

    /**
     * 条件修改
     */
    override fun requestUpdate(vararg student: Student) {
        collectModel.requestUpdate(this, *student)
    }

    /**
     * 条件删除
     */
    override fun requestDelete(vararg student: Student) {
        collectModel.requestDelete(this, *student)
    }

    /**
     * 获取全部
     */
    override fun requestQueryAll() {
        collectModel.requestQueryAll(this)
    }

    /**
     * 删除全部
     */
    override fun requestDeleteAll() {
        collectModel.requestQueryAll(this)
    }

    override fun unAttachView() {
        collectView = null
        collectModel
    }

    /**
     * 最终的回调
     */
    override fun showAllStudent(students: List<Student>?) {
        if (students != null && students.isNotEmpty()) {
            collectView?.showAllStudent(students)
        }
    }

    override fun deleteAllStudent() {
        collectView?.deleteAllStudent()
    }
}