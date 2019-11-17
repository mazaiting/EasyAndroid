package com.mazaiting.easy.base.activity

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.mazaiting.easy.app.BaseApplication
import com.mazaiting.easy.base.mvp.IView
import com.mazaiting.easy.base.mvp.IBaseView
import com.mazaiting.easy.base.presenter.BasePresenter
import javax.inject.Inject

/**
 * Activity基类
 * @author mazaiting
 * @date 2018/2/5
 */

abstract class BaseActivity<T : BasePresenter> : AppCompatActivity(), IBaseView,
    IView {
    /**主持人 */
    @Nullable
    @Inject
    protected var mPresenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 创建布局
        rootView = layoutInflater.inflate(contentLayout, null)
        // 设置布局内容
        setContentView(rootView)
        // 注入ApplicationComponent
        inject(BaseApplication.instance.applicationComponent)
        // 绑定布局--与Presenter
        attachView()
        // 绑定布局
        rootView?.let { bindView(it, savedInstanceState) }
        // 初始化数据
        initData()
    }

    /**
     * 绑定布局
     */
    private fun attachView() {
        if (null != mPresenter) {
            mPresenter!!.attachView(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 与Presenter解除绑定
        if (null != mPresenter) {
            mPresenter!!.detachView()
        }
    }
}
