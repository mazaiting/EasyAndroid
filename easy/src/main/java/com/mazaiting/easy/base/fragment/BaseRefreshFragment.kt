package com.mazaiting.easy.base.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mazaiting.easy.base.mvp.IBaseView
import com.mazaiting.easy.base.presenter.BasePresenter
import kotlinx.android.synthetic.main.refresh.*

/**
 * 刷新界面Fragment
 * Created by mazaiting on 2018/4/25.
 */
abstract class BaseRefreshFragment<T : BasePresenter<IBaseView>> : BaseLoadingFragment<T>() {
    /**适配器 */
    protected var mAdapter: BaseQuickAdapter<Any, BaseViewHolder>? = null
    /**
     * 获取布局管理者
     * @return 布局管理者
     */
    protected val layoutManager: RecyclerView.LayoutManager
        get() = LinearLayoutManager(this.context)
    override fun bindView(view: View, savedInstanceState: Bundle?) {
        super.bindView(view, savedInstanceState)
        // 设置下拉刷新的颜色
        swipeLayout.setColorSchemeColors(Color.rgb(47, 223, 189))
        // 设置布局方向
        recyclerView.layoutManager = layoutManager
        // 创建适配器
        mAdapter = setAdapter()
        // 打开加载动画
        mAdapter?.openLoadAnimation(BaseQuickAdapter.ALPHAIN)
        // 设置适配器
        recyclerView.adapter = mAdapter
    }

    /**
     * 设置列表适配器
     * @return 返回适配器
     */
    protected abstract fun setAdapter(): BaseQuickAdapter<Any, BaseViewHolder>
}
