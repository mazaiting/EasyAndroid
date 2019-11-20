package com.mazaiting.easy.base.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mazaiting.easy.base.mvp.IBaseView
import com.mazaiting.easy.base.presenter.BasePresenter
import kotlinx.android.synthetic.main.refresh.*

/**
 * 带刷新的Activity
 * @author mazaiting
 * @date 2018/3/23
 * @description
 * T: 列表参数类型
 * V: IBaseView 子类
 * P: BasePresenter 子类
 */
abstract class BaseRefreshToolbarActivity<T, V: IBaseView, P: BasePresenter<V>> : BaseToolbarActivity<V, P>() {
    /**适配器 */
    protected var adapter: BaseQuickAdapter<T, BaseViewHolder>? = null
    /**
     * 获取布局管理者
     * @return 布局管理者
     */
    private val layoutManager: RecyclerView.LayoutManager = getLayoutManager()

    override fun bindView(view: View, savedInstanceState: Bundle?) {
        super.bindView(view, savedInstanceState)
        // 检测 SwipeRefreshLayout 是否为空
        if (null == swipeRefreshLayout) {
            throw RuntimeException("Please use SwipeRefreshLayout in your layout file.")
        }
        // 检测 RecyclerView 是否为空
        if (null == recyclerView) {
            throw RuntimeException("Please use RecyclerView in your layout file.")
        }
        // 设置下拉刷新的颜色
        swipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189))
        // 设置布局方向
        recyclerView.layoutManager = layoutManager
        // 获取 Adapter
        adapter = getBaseAdapter()
        // 打开加载动画
        adapter?.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        // 设置适配器
        recyclerView.adapter = adapter
    }

    /**
     * 获取适配器
     */
    protected abstract fun getBaseAdapter(): BaseQuickAdapter<T, BaseViewHolder>

    /**
     * 获取 SwipeRefreshLayout
     */
    protected fun getSwipeRefreshLayout(): SwipeRefreshLayout = swipeRefreshLayout

    /**
     * 获取 RecyclerView
     */
    protected fun getRecyclerView(): RecyclerView = recyclerView

    /**
     * 获取 LayoutManager
     */
    protected fun getLayoutManager() :RecyclerView.LayoutManager = LinearLayoutManager(this)
}
