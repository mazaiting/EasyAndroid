package com.mazaiting.easydemo.function.fragment

import com.mazaiting.easy.base.activity.BaseToolbarActivity
import com.mazaiting.easy.base.component.ApplicationComponentImpl
import com.mazaiting.easy.base.component.IApplicationComponent
import com.mazaiting.easydemo.R
import com.mazaiting.easydemo.base.component.DaggerCustomComponent

/**
 * Fragment 测试类
 */
class FragmentActivity(
    override val contentLayout: Int = R.layout.activity_fragment
) : BaseToolbarActivity<FragmentContract.View, FragmentPresenter>(), FragmentContract.View {
    override fun inject(applicationComponent: IApplicationComponent) {
        DaggerCustomComponent
            .builder()
            .applicationComponentImpl(applicationComponent as ApplicationComponentImpl)
            .build()
            .inject(this)
    }

    override fun initData() {

    }
}
