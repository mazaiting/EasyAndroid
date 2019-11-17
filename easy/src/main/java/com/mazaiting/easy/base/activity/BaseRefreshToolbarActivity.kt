package com.mazaiting.easy.base.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mazaiting.easy.base.presenter.BasePresenter
import kotlinx.android.synthetic.main.refresh.*

/**
 * 带刷新的Activity
 * @author mazaiting
 * @date 2018/3/23
 */

abstract class BaseRefreshToolbarActivity<T : BasePresenter> : BaseToolbarActivity<T>() {
    /**适配器 */
    protected lateinit var mAdapter: BaseQuickAdapter<Any, BaseViewHolder>

    /**
     * 获取布局管理者
     * @return 布局管理者
     */
    protected val layoutManager: RecyclerView.LayoutManager
        get() = LinearLayoutManager(this)

    override fun bindView(view: View, savedInstanceState: Bundle?) {
        super.bindView(view, savedInstanceState)
        // 设置下拉刷新的颜色
        swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189))
        // 设置布局方向
        recyclerView.layoutManager = layoutManager
        // 创建适配器
        mAdapter = setAdapter()
        // 打开加载动画
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        // 设置适配器
        recyclerView.adapter = mAdapter
    }

    /**
     * 设置列表适配器
     * @return 返回适配器
     */
    protected abstract fun setAdapter(): BaseQuickAdapter<Any, BaseViewHolder>
}
