package com.v.module_jetpack.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.v.module_jetpack.R

/**
 * 定义RecyclerView的适配器，但是注意，这个适配器也比较特殊，必须继承自PagingDataAdapter
 *
 * 相比于一个传统的RecyclerView Adapter，这里最特殊的地方就是要提供一个COMPARATOR。
 * 因为Paging 3在内部会使用DiffUtil来管理数据变化，所以这个COMPARATOR是必须的。如果你以前用过DiffUtil的话，对此应该不会陌生。
 * 除此之外，我们并不需要传递数据源给到父类，因为数据源是由Paging 3在内部自己管理的。同时也不需要重写getItemCount()函数了，原因也是相同的，有多少条数据Paging 3自己就能够知道。
 * 其他部分就和普通的RecyclerView Adapter没什么两样了，相信大家都能够看得明白。
 */
class RepoAdapter : PagingDataAdapter<Repo, RepoAdapter.ViewHolder>(COMPARATOR) {
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name_text)
        val description: TextView = itemView.findViewById(R.id.description_text)
        val starCount: TextView = itemView.findViewById(R.id.star_count_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = getItem(position)
        if (repo != null) {
            holder.name.text = repo.name
            holder.description.text = repo.description
            holder.starCount.text = repo.starCount.toString()
        }
    }
}