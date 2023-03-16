package com.v.module_jetpack.paging

import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.v.module_base.BaseTitleBarActivity
import com.v.module_jetpack.R
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

/**
 * Paging 库可帮助您加载和显示来自本地存储或网络中更大的数据集中的数据页面。此方法可让您的应用更高效地利用网络带宽和系统资源。
 * https://blog.csdn.net/Alexwll/article/details/83246201
 *
 * https://guolin.blog.csdn.net/article/details/114707250?spm=1001.2014.3001.5502
 */
class PagingActivity : BaseTitleBarActivity() {

    private val viewModel by lazy { ViewModelProvider(this).get(PagingViewModel::class.java) }

    private val repoAdapter = RepoAdapter()

    override fun setTitle(): String {
        return "Paging"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_paging
    }

    @OptIn(InternalCoroutinesApi::class)
    override fun processLogical() {
        super.processLogical()
        setContentView(R.layout.activity_paging)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = repoAdapter
        lifecycleScope.launch {
            viewModel.getPagingData().collect(object : FlowCollector<PagingData<Repo>> {
                override suspend fun emit(value: PagingData<Repo>) {
                    repoAdapter.submitData(value)
                }
            })
        }
        repoAdapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    progressBar.visibility = View.INVISIBLE
                    recyclerView.visibility = View.VISIBLE
                }
                is LoadState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.INVISIBLE
                }
                is LoadState.Error -> {
                    val state = it.refresh as LoadState.Error
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(this, "Load Error: ${state.error.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}