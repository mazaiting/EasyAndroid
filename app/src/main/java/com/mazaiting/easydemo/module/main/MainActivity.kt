package com.mazaiting.easydemo.module.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mazaiting.easy.app.IApplicationComponent
import com.mazaiting.easy.base.activity.BaseRefreshToolbarActivity
import com.mazaiting.easy.base.component.ApplicationComponentImpl
import com.mazaiting.easydemo.R

class MainActivity(
    override val contentLayout: Int = R.layout.activity_main
) : BaseRefreshToolbarActivity<String, MainContract.View, MainPresenter>(), MainContract.View {

    override fun getDisplayHomeAsUpEnabled(): Boolean = false

    override fun getBaseAdapter(): BaseQuickAdapter<String, BaseViewHolder> = MainAdapter()

    override fun inject(applicationComponent: IApplicationComponent) {
        DaggerMainComponent
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
            Toast.makeText(this@MainActivity, "下拉刷新", Toast.LENGTH_SHORT).show()
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
