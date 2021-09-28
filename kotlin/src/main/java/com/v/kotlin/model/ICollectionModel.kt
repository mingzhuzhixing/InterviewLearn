package com.v.kotlin.model

import com.v.kotlin.bean.Student
import com.v.kotlin.database.model.LocalRoomRequestManager
import com.v.kotlin.presenter.ICollectPresenter

/**
 * ClassName: ICollectionModel
 * Description:
 * @package_name com.v.kotlin.model
 * @author zhuming
 * @date 2021/9/28 4:34 下午
 */
interface ICollectionModel {
    fun requestInsert(listener: ICollectPresenter.OnCollectLister, vararg student: Student)
    fun requestUpdate(listener: ICollectPresenter.OnCollectLister, vararg student: Student)
    fun requestDelete(listener: ICollectPresenter.OnCollectLister, vararg student: Student)

    /**
     * 获取全部
     */
    fun requestQueryAll(listener: ICollectPresenter.OnCollectLister)

    /**
     * 删除全部
     */
    fun requestDeleteAll(listener: ICollectPresenter.OnCollectLister)

    fun requestCancel()
}

class ICollectionModelImpl : ICollectionModel {

    override fun requestInsert(listener: ICollectPresenter.OnCollectLister, vararg student: Student) {
        LocalRoomRequestManager.getInstance().insertStudents(*student)
    }

    override fun requestUpdate(listener: ICollectPresenter.OnCollectLister, vararg student: Student) {
        LocalRoomRequestManager.getInstance().updateStudents(*student)
    }

    override fun requestDelete(listener: ICollectPresenter.OnCollectLister, vararg student: Student) {
        LocalRoomRequestManager.getInstance().deleteStudents(*student)
    }

    override fun requestQueryAll(listener: ICollectPresenter.OnCollectLister){
        val queryAllStudents = LocalRoomRequestManager.getInstance().queryAllStudents()
        listener.showAllStudent(queryAllStudents)
    }

    override fun requestDeleteAll(listener: ICollectPresenter.OnCollectLister) {
        LocalRoomRequestManager.getInstance().deleteAllStudents()
        listener.deleteAllStudent()
    }

    override fun requestCancel() {

    }
}