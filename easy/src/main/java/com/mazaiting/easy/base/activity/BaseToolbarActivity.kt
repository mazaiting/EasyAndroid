package com.mazaiting.easy.base.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.mazaiting.easy.base.mvp.IBaseView

import com.mazaiting.easy.base.presenter.BasePresenter
import kotlinx.android.synthetic.main.toolbar.*


/**
 * 带有Toolbar的Activity
 * 注意: Activity 的主题必须是无 ActionBar 的
 * @author mazaiting
 * @date 2018/3/23
 * @description
 * V: IBaseView 子类
 * P: BasePresenter 子类
 */
abstract class BaseToolbarActivity<V: IBaseView, P: BasePresenter<V>> : BaseLoadingActivity<V, P>() {
    /**
     * 是否显示返回键
     */
    private var showHomeAsUp: Boolean = false

    /**
     * 如果重写此方法，在方法体内先调用父类的bindView方法
     * @param view 视图
     * @param savedInstanceState bundle
     */
    override fun bindView(view: View, savedInstanceState: Bundle?) {
        super.bindView(view, savedInstanceState)
        // 检测 toolbar 是否为空
        if (null == toolbar) {
            throw RuntimeException("Please use toolbar in your layout file.")
        }
        // 设置导航栏
        setSupportActionBar(toolbar)
        // 获取是否显示返回键按钮
        showHomeAsUp = getDisplayHomeAsUpEnabled()
        // 设置显示返回键
        supportActionBar?.setDisplayHomeAsUpEnabled(showHomeAsUp)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * 获取 Toolbar
     */
    protected open fun getToolbar(): Toolbar = toolbar

    /**
     * 获取是否显示 Toolbar 返回键
     */
    protected open fun getDisplayHomeAsUpEnabled(): Boolean = true
}
