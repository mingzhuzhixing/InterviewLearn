package com.v.kotlin_app.fragment

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.stx.xhb.xbanner.XBanner
import com.v.kotlin_app.R
import com.v.kotlin_app.adapter.HomeAdapter
import com.v.kotlin_app.base.BaseFragment
import com.v.kotlin_app.bean.BannerDataBean
import com.v.kotlin_app.bean.HomeDataBean
import com.v.kotlin_app.ivew.IHomeView
import com.v.kotlin_app.presenter.HomePresenterImpl
import com.v.module_recyclerview.decoration.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * 首页
 * https://github.com/xiaohaibin/XBanner
 */
class HomeFragment : BaseFragment<HomePresenterImpl>(), IHomeView {
    lateinit var mAdapter: HomeAdapter;
    override fun getLayoutId() = R.layout.fragment_home

    override fun initData() {
        mAdapter = HomeAdapter(requireActivity())

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }
        //设置分割线
        val itemDecoration: RecyclerView.ItemDecoration = HorizontalDividerItemDecoration.Builder(requireActivity())
                .colorResId(R.color.color_4bba95)
                .sizeResId(R.dimen.y2)
                .build()
        recyclerView.addItemDecoration(itemDecoration)
    }

    override fun createPresenter(): HomePresenterImpl {
        return HomePresenterImpl(this)
    }

    override fun processLogical() {
        mPresenter?.getBannerData()
        mPresenter?.getHomeData()
    }

    override fun onSuccessData(data: HomeDataBean) {
        mAdapter.setClearAndData(data.datas)
    }

    override fun onBannerSuccess(data: List<BannerDataBean>) {
        val images = mutableListOf<String>()
        val titles = mutableListOf<String>()
        for (item in data) {
            images.add(item.imagePath)
            titles.add(item.title)
        }
        x_banner.setData(images, titles)
        x_banner.loadImage(object : XBanner.XBannerAdapter {
            override fun loadBanner(banner: XBanner?, model: Any?, view: View, position: Int) {
                //设置图片圆角角度
                val roundedCorners = RoundedCorners(20);
                //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
                // RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
                val options = RequestOptions.bitmapTransform(roundedCorners);
                Glide.with(activity!!).load(images[position]).apply(options).into(view as ImageView)
            }
        })
        x_banner.setOnItemClickListener(object : XBanner.OnItemClickListener {
            override fun onItemClick(banner: XBanner?, model: Any?, view: View?, position: Int) {
                Toast.makeText(requireContext(), "点击了第" + position + "个", Toast.LENGTH_SHORT).show()
            }
        })
    }
}