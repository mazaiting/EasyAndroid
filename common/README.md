# Android Kotlin Quick Development Tool

#### 介绍

Android 快速开发工具

#### 开发工具

1. Android Studio 3.5.2
2. Kotlin 1.3.60

#### 项目进度

- 11 月 22 日: v0.0.1 akdt-common 添加 Toast 和 Log 模块, 协程请求添加
- 11 月 26 日: v0.0.2 akdt-common 添加 Intent 模块

#### 使用

- Toast

```
toast("test")
longToast("test")
```

- Log

```
// 开启日志
LOG_DEBUG = true
// 日志打印
debug("test")
error("test")
```

- Intent

```
// 开始 Activity
startActivity<FragmentActivity>("key" to "value")
// 获取意图
intentFor<FragmentActivity>()
// 开启服务
startService<FragmentService>("key" to "value")
```


#### Contribution

1. [简书地址](https://www.jianshu.com/u/5d2cb4bfeb15)
2. [码云地址](https://gitee.com/)
3. [邮箱](mailto:zaitingma@foxmail.com)
4. [新浪微博](http://blog.sina.com.cn/mazaiting)

