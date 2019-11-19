package com.mazaiting.easydemo.module.main

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mazaiting.easy.app.IApplicationComponent
import com.mazaiting.easy.base.activity.BaseRefreshToolbarActivity
import com.mazaiting.easydemo.R

class MainActivity(
    override val contentLayout: Int = R.layout.activity_main
) : BaseRefreshToolbarActivity<MainContract.View, MainPresenter>(), MainContract.View {

    override val adapter: BaseQuickAdapter<*, BaseViewHolder>?
        get() = MainAdapter()

    override fun inject(applicationComponent: IApplicationComponent) {

    }

    override fun initData() {

    }

    override fun loadSuccess() {

    }


}
