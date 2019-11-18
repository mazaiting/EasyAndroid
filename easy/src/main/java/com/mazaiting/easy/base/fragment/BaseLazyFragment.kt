package com.mazaiting.easy.base.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import com.mazaiting.easy.base.mvp.IBaseView

import com.mazaiting.easy.base.presenter.BasePresenter

/**
 * 懒加载Fragment基类
 * @author mazaiting
 * @date 2018/2/5
 */
abstract class BaseLazyFragment<T : BasePresenter<IBaseView>> : BaseFragment<T>() {
    /**标记视图是否已经准备好 */
    private var isViewPrepared: Boolean = false

    /**
     * 设置为当前未懒加载的Fragment
     * @return true 为懒加载数据
     */
    override val isLazyFetchData: Boolean
        get() = true

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 标记视图已经创建好
        isViewPrepared = true
        // 懒加载数据
        lazyFetchDataIfPrepared()
    }

    override fun initData() {

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        // 判断是否用户可见
        if (isVisibleToUser) {
            // 如果可见，则进行懒加载数据
            lazyFetchDataIfPrepared()
        }
    }

    /**
     * 如果准备好，进行懒加载数据
     */
    private fun lazyFetchDataIfPrepared() {
        // 判断是否用户可见， 是否为加载过数据， 是否视图已经准备好
        if (userVisibleHint && !hasFetchData && isViewPrepared) {
            // 设置加载过数据
            hasFetchData = true
            // 懒加载数据
            lazyFetchData()
        }
    }

    /**
     * 懒加载数据的抽象方法
     */
    protected abstract fun lazyFetchData()

}
