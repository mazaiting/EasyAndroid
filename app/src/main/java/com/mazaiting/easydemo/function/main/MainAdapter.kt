package com.mazaiting.easydemo.function.main

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mazaiting.easydemo.R

/**
 * 主页适配器
 */
class MainAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_main) {
    override fun convert(helper: BaseViewHolder?, text: String) {
        helper?.let {
            // 设置标题
            it.setText(R.id.tv_title, text)
            // 添加点击事件
            it.addOnClickListener(R.id.tv_title)
        }
    }
}
