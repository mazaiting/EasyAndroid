package com.mazaiting.easy.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment

import com.mazaiting.easy.app.BaseApplication
import com.mazaiting.easy.base.mvp.IView
import com.mazaiting.easy.base.mvp.IBaseView
import com.mazaiting.easy.base.presenter.BasePresenter
import javax.inject.Inject

/**
 * Fragment基类
 * @author mazaiting
 * @date 2018/2/5
 */

abstract class BaseFragment<T : BasePresenter<IBaseView>> : Fragment(), IView, IBaseView {
    /**主持人 */
    @Nullable @Inject lateinit var presenter: T
    /**判断是否加载过数据 */
    protected var hasFetchData = false

    /**
     * 判断是否是懒加载, 默认为false, 即为非懒加载
     * @return true, 是懒加载；false, 不是懒加载
     */
    protected open val isLazyFetchData: Boolean
        get() = false

    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View {
        // 判断View是否为空
        if (null != rootView) {
            // 不为空时获取它的父布局
            val parent = rootView!!.parent as ViewGroup
            // 判断父布局是否为空
            parent.removeView(rootView)
        } else {
            // 为空时创建View
            rootView = inflater.inflate(contentLayout, container, false)
        }
        return rootView as View
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 注入当前Fragment
        inject(BaseApplication.instance.applicationComponent)
        // 视图与Presenter绑定
        attachView()
        // 绑定View
        bindView(view, savedInstanceState)
        // 判断是否懒加载数据
        if (!isLazyFetchData) {
            // 初始化数据
            initData()
            // 设置为已经加载数据
            hasFetchData = true
        }
    }

    /**
     * 绑定布局
     */
    private fun attachView() {
        presenter?.attachView(this)
    }

    override fun onDestroy() {
        presenter?.detachView()
        super.onDestroy()
    }
}
