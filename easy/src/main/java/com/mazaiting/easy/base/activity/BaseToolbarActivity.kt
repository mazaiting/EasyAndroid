package com.mazaiting.easy.base.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.mazaiting.easy.base.mvp.IBaseView

import com.mazaiting.easy.base.presenter.BasePresenter
import kotlinx.android.synthetic.main.toolbar.*


/**
 * 带有Toolbar的Activity
 * @author mazaiting
 * @date 2018/3/23
 */

abstract class BaseToolbarActivity<in V: IBaseView, P: BasePresenter<V>> : BaseLoadingActivity<V, P>() {

    /**
     * 如果重写此方法，在方法体内先调用父类的bindView方法
     * @param view 视图
     * @param savedInstanceState bundle
     */
    override fun bindView(view: View, savedInstanceState: Bundle?) {
        super.bindView(view, savedInstanceState)
        if (null == toolbar) {
            throw RuntimeException("Please use toolbar in your layout file.")
        }
        // 设置导航栏
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
