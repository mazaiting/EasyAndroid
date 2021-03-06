package com.mazaiting.easy.base.activity

import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.mazaiting.easy.app.BaseApplication
import com.mazaiting.easy.base.mvp.IBaseView
import com.mazaiting.easy.base.mvp.IView
import com.mazaiting.easy.base.presenter.BasePresenter
import javax.inject.Inject

/**
 * Activity基类
 * @author mazaiting
 * @date 2018/2/5
 * @description
 * V: IBaseView 子类
 * P: BasePresenter 子类
 */
abstract class BaseActivity<V: IBaseView, P: BasePresenter<V>> : AppCompatActivity(), IBaseView, IView {
    /** 根布局 */
    override var rootView: View? = null
    /**主持人 */
    @Nullable @Inject @JvmField var presenter: P? = null

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
    @Suppress("UNCHECKED_CAST")
    private fun attachView() {
        presenter?.attachView(this as V)
    }

    override fun onDestroy() {
        // 与Presenter解除绑定
        presenter?.detachView()
        super.onDestroy()
    }
}
