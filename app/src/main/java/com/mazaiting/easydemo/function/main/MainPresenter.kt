package com.mazaiting.easydemo.function.main

import com.mazaiting.common.debug
import com.mazaiting.common.response
import com.mazaiting.easy.base.presenter.BasePresenter
import com.mazaiting.easydemo.base.service.TestApi
import com.mazaiting.easydemo.bean.Item
import com.mazaiting.easydemo.function.fragment.FragmentActivity
import javax.inject.Inject

/***
 *
 *
 *                                                    __----~~~~~~~~~~~------___
 *                                   .  .   ~~//====......          __--~ ~~
 *                   -.            \_|//     |||\\  ~~~~~~::::... /~
 *                ___-==_       _-~o~  \/    |||  \\            _/~~-
 *        __---~~~.==~||\=_    -_--~/_-~|-   |\\   \\        _/~
 *    _-~~     .=~    |  \\-_    '-~7  /-   /  ||    \      /
 *  .~       .~       |   \\ -_    /  /-   /   ||      \   /
 * /  ____  /         |     \\ ~-_/  /|- _/   .||       \ /
 * |~~    ~~|--~~~~--_ \     ~==-/   | \~--===~~        .\
 *          '         ~-|      /|    |-~\~~       __--~~
 *                      |-~~-_/ |    |   ~\_   _-~            /\
 *                           /  \     \__   \/~                \__
 *                       _--~ _/ | .-~~____--~-/                  ~~==.
 *                      ((->/~   '.|||' -_|    ~~-/ ,              . _||
 *                                 -_     ~\      ~~---l__i__i__i--~~_/
 *                                 _-~-__   ~)  \--______________--~~
 *                               //.-~~~-~_--~- |-------~~~~~~~~
 *                                      //.-~~~--\
 *                               神兽保佑
 *                              代码无BUG!
 * @author mazaiting
 * @date 2019-11-18
 * @description 主页面主持人
 */
class MainPresenter @Inject constructor(private val testApi: TestApi) : BasePresenter<MainContract.View>(),
    MainContract.Presenter {

    override fun loadData() {
        // 创建可变列表
        val list = mutableListOf<Item>()
        list.add(Item("Fragment 测试", FragmentActivity::class.java))
        // 添加数据
        for (i in 1..10) {
            list.add(Item("Test$i", FragmentActivity::class.java))
        }

        // 网络请求
        response({ testApi.getDataAsync() }, {
            // 成功
            debug(it)
        }, {
            // 失败
            debug(it)
        })


        view?.loadSuccess(list)
    }

}
