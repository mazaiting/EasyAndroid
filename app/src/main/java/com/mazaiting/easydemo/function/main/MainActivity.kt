package com.mazaiting.easydemo.function.main

import android.os.Bundle
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mazaiting.common.debug
import com.mazaiting.easy.base.activity.BaseRefreshToolbarActivity
import com.mazaiting.easy.base.component.ApplicationComponentImpl
import com.mazaiting.easy.base.component.IApplicationComponent
import com.mazaiting.easydemo.R
import com.mazaiting.easydemo.base.component.DaggerCustomComponent

class MainActivity(
    override val contentLayout: Int = R.layout.activity_main
) : BaseRefreshToolbarActivity<String, MainContract.View, MainPresenter>(), MainContract.View {

    override fun getDisplayHomeAsUpEnabled(): Boolean = false

    override fun getBaseAdapter(): BaseQuickAdapter<String, BaseViewHolder> = MainAdapter()

    override fun inject(applicationComponent: IApplicationComponent) {
        DaggerCustomComponent
            .builder()
            .applicationComponentImpl(applicationComponent as ApplicationComponentImpl)
            .build()
            .inject(this)
    }

    override fun bindView(view: View, savedInstanceState: Bundle?) {
        super.bindView(view, savedInstanceState)
        // 下拉刷新
        getSwipeRefreshLayout().setOnRefreshListener {
            getSwipeRefreshLayout().isRefreshing = false
            debug("下拉刷新")
        }
        // 设置点击事件
        adapter?.setOnItemChildClickListener { _, _, position ->
//            adapter?.getItem(position).run {
//                Toast.makeText(this@MainActivity, this, Toast.LENGTH_SHORT).show()
//            }
        }
    }

    override fun initData() {
        // 加载数据
        presenter?.loadData()
    }

    override fun loadSuccess(list: MutableList<String>) {
        adapter?.setNewData(list)
    }

}
