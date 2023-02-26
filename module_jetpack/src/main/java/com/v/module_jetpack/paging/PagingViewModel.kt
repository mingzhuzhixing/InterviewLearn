package com.v.module_jetpack.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

/**
 * 定义一个ViewModel，因为Activity是不可以直接和Repository交互的，要借助ViewModel才可以。新建一个MainViewModel类，
 */
class PagingViewModel : ViewModel() {

    /**
     * cachedIn()函数，这是用于将服务器返回的数据在viewModelScope这个作用域内进行缓存，假如手机横竖屏发生了旋转导致Activity重新创建，Paging 3就可以直接读取缓存中的数据，而不用重新发起网络请求了。
     */
    fun getPagingData(): Flow<PagingData<Repo>> {
        return Repository.getPagingData().cachedIn(viewModelScope)
    }
}
