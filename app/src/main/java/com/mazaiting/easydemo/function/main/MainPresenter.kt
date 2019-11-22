package com.mazaiting.easydemo.function.main

import com.mazaiting.common.debug
import com.mazaiting.common.response
import com.mazaiting.easy.base.presenter.BasePresenter
import com.mazaiting.easydemo.base.service.TestApi
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
class MainPresenter @Inject constructor(val testApi: TestApi) : BasePresenter<MainContract.View>(),
    MainContract.Presenter {

    override fun loadData() {
        // 创建可变列表
        val list = mutableListOf<String>()
        // 添加数据
        for (i in 0..10) {
            list.add("Test$i")
        }
//        val handler = CoroutineExceptionHandler { _, exception ->
//            verbose("Caught $exception")
//        }
//        // 协程
//        GlobalScope.launch(handler) {
//            withContext(Dispatchers.IO) {
//                val data = testApi.getDatas().await()
//                error(data)
//            }
//        }

//        response {
//            request { testApi.getDatas().await() }
//        }
//         成功
            response({testApi.getDataAsync()}, {
                debug(it)
            }, {
                debug(it)
            })


        view?.loadSuccess(list)
    }

}

data class Data(
    val data: List<Test>
)

data class Test(
    val name: String
)