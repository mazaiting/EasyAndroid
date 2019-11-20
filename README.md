# EasyAndroid

#### 介绍
搭建Android的快速开发框架

#### 软件架构
整体架构使用 MVP 模式, 开发语言为 Kotlin, RxJava2 + Retrofit2 + Okhttp3 网络请求 Dagger2 依赖注入, EventBus 事件通信协议, Material Design 设计风格

#### 开发工具

1.  Android Studio 3.0 +
2.  RE 文件管理器

#### Framework

| Name        | Version           | Description  |
| ------------- |:-------------:| -----:|
| [Dagger2](https://dagger.dev/)      | 2.25.2 | 依赖注入框架 |
| [EventBus](http://greenrobot.org/eventbus/)      | 3.1.1 | 事件通信 |
| [BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)      | 2.9.34 | RecyclerView 工具 |
| [Rxjava2](https://github.com/ReactiveX/RxJava)      | 2.2.14 | 可观察序列组合异步和基于事件的程序 |
| [Rxandroid](https://github.com/ReactiveX/RxAndroid)      | 2.1.1 | Android 中绑定 RxJava2 |
| [Retrofit](http://square.github.io/retrofit/)      | 2.6.2 | 使用注解描述 HTTP |
| [Okhttp3](http://square.github.io/okhttp/)      | 4.2.1 | 高效的HTTP客户端 |
| [PersistentCookieJar](https://github.com/franmontiel/PersistentCookieJar)      | v1.0.1 | 持久化 Cookie |
| [Crash](https://github.com/mazaiting/Crash)      | 1.0.5 | 全局异常捕获工具 |
| [Log](https://github.com/mazaiting/Log)      | 1.0.0 | 日志打印工具 |
| [Permission](https://github.com/mazaiting/permission_android)      | 1.0.1 | Android 权限检测工具 |
| [SharedPreferences](https://github.com/mazaiting/SharedPreferencesUtil)      | 1.0.0 | SharedPreferences 工具类 |
| [Glide](https://github.com/bumptech/glide)      | 4.9.0 | 图片加载与缓存库 |
| [Agentweb](https://github.com/Justson/AgentWeb)      | 4.0.2 | WebView 框架 |
| [AVLoadingIndicatorView](https://github.com/81813780/AVLoadingIndicatorView)      | 2.1.3 | 加载动画 |
| [Room](https://github.com/81813780/AVLoadingIndicatorView)      | 2.1.3 | 加载动画 |

#### Project Progress

- 11 月 17 日: 项目初始化, 添加 widget 与 easy 模块
- 11 月 18 日: 完成 dagger 全局应用组件的注入，网络与 SharedPreferences 的注入
- 11 月 19 日: 完成 Activity 的 MVP 使用

#### Size of the picture 

- Dpi, resolution and icon size relationship

| Name        | Resolution           | Boot Icon Size  |
|:-------------:|:-------------:|:-----:|
| L DPI ( 120 DPI ) | 240*320px | 36 x 36 px |
| M DPI (160 DPI ) | 320*480px | 48 x 48 px |
| H DPI ( 240 DPI ) | 480*800px | 72 x 72 px |
| XH DPI ( 320 DPI ) | 1280*720px | 96 x 96 px |
| XXH DPI( 480 DPI ) | 1920*1080px | 144 x 144 px |
| XXXH DPI( 640 DPI ) | 3840*2160px | 192 x 192 px |

- PX to DP

| Name        | DPI           | Proportion (based on mdpi 1)  | Conversion relationship with px |
|:-------------:|:-------------:|:----------:|:----------:|
| lpdi | 120 DPI | 0.75 | 1 dp = 0.75 px |
| mdpi | 160 DPI | 1 | 1 dp = 1 px |
| hdpi | 240 DPI | 1.5 | 1 dp = 1.5 px |
| xhdpi	 | 320 DPI | 2 | 1 dp = 2 px |
| xxhdpi | 480 DPI | 3 | 1 dp = 3 px |
| xxxhdpi | 640 DPI | 4 | 1 dp = 4 px |

#### Contribution

1. [简书地址](https://www.jianshu.com/u/5d2cb4bfeb15)
2. [码云地址](https://gitee.com/)
3. [邮箱](mailto:zaitingma@foxmail.com)
4. [新浪微博](http://blog.sina.com.cn/mazaiting)

