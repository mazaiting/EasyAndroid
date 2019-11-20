package com.mazaiting.easy.base.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.fragment.app.LoadingDialogFragment
import com.mazaiting.easy.base.mvp.IBaseView
import com.mazaiting.easy.base.mvp.ILoading
import com.mazaiting.easy.base.presenter.BasePresenter
import com.mazaiting.easy.config.Constant

/**
 * 加载页面基类
 * @author mazaiting
 * @description
 * V: IBaseView 子类
 * P: BasePresenter 子类
 */

abstract class BaseLoadingActivity<V: IBaseView, P: BasePresenter<V>> : BaseActivity<V, P>(), ILoading {

    /**加载进度条 */
    private var mLoadingDialogFragment: LoadingDialogFragment? = null

    override fun bindView(view: View, savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onShowLoading() {
        if (null == mLoadingDialogFragment) {
            // 创建进度条Fragment
            mLoadingDialogFragment = LoadingDialogFragment()
        }
        // 显示
        mLoadingDialogFragment!!.show(supportFragmentManager, Constant.DIALOG_LOADING)
    }

    override fun onShowSuccess() {
        onHideLoading()
    }

    override fun onHideLoading() {
        // 可用
        //        if (mLoadingDialogFragment.isCancelable()) {
        //            mLoadingDialogFragment.dismiss();
        //        }
        // 可用，如果出现问题，使用上方的代码
        mLoadingDialogFragment!!.dismissAllowingStateLoss()
    }

    override fun onShowFailed(message: String) {
        onHideLoading()
    }
}
