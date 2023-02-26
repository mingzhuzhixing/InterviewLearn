package com.v.module_jetpack.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

object Repository {
    private const val PAGE_SIZE = 50

    private val gitHubService = GitHubService.create()

    /**
     * 用到了协程的Flow。我无法在这里展开解释Flow是什么，你可以简单将它理解成协程中对标RxJava的一项技术。
     *
     * 在getPagingData()函数当中，这里创建了一个Pager对象，并调用.flow将它转换成一个Flow对象。
     * 在创建Pager对象的时候，我们指定了PAGE_SIZE，也就是每页所包含的数据量。
     * 又指定了pagingSourceFactory，并将我们自定义的RepoPagingSource传入，这样Paging 3就会用它来作为用于分页的数据源了。
     */
    fun getPagingData(): Flow<PagingData<Repo>> {
        return Pager(
                config = PagingConfig(PAGE_SIZE),
                pagingSourceFactory = { RepoPagingSource(gitHubService) }
        ).flow
    }

}