package com.mazaiting.easydemo.module.main

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mazaiting.easydemo.R

/**
 * 主页适配器
 */
class MainAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_main) {
    override fun convert(helper: BaseViewHolder, text: String) {
        helper.setText(R.id.tv_title, text)
    }
}
