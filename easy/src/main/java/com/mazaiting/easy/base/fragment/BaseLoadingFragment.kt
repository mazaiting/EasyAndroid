package com.mazaiting.easy.base.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.LoadingDialogFragment
import com.mazaiting.easy.base.mvp.ILoading
import com.mazaiting.easy.base.presenter.BasePresenter
import com.mazaiting.easy.config.Constant

/**
 * 带有加载进度条的Fragment基类
 */

abstract class BaseLoadingFragment<T : BasePresenter> : BaseLazyFragment<T>(), ILoading {
    /**加载进度条 */
    private var mLoadingDialogFragment: LoadingDialogFragment? = null

    override fun bindView(view: View, savedInstanceState: Bundle?) {

    }

    override fun onShowLoading() {
        if (null == mLoadingDialogFragment) {
            // 创建进度条Fragment
            mLoadingDialogFragment = LoadingDialogFragment()
        }
        activity?.let {
            // 显示
            mLoadingDialogFragment?.show(it.supportFragmentManager, Constant.DIALOG_LOADING)
        }
    }

    override fun onShowSuccess() {
        onHideLoading()
    }

    override fun onShowFailed(message: String) {
        onHideLoading()
    }

    override fun onHideLoading() {
        // 可用
        //        if (mLoadingDialogFragment.isCancelable()) {
        //            mLoadingDialogFragment.dismiss();
        //        }
        // 可用，如果出现问题，使用上方的代码
        mLoadingDialogFragment?.dismissAllowingStateLoss()
    }

}
