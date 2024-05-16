<h1 align="center">Welcome to 鼠鼠取餐 👋</h1>

[<img src="https://shu-silence.oss-cn-shanghai.aliyuncs.com/img/2022/t.png" alt="logo" width=500px />](http://order.itbear666.top:82/)


> 2021-2022 学年春季学期《JavaEE开发技术》大作业 --->>> 鼠鼠取餐 (shu取餐App)

### 🏠 [项目主页](https://github.com/SHU-Silence/shushuCloudOrder)

本项目为前后端分离项目，前端采用Uni-app与Vue，UI采用Uview，后端采用Springboot整合SSM，并采用Mybatis Plus简化CRUD操作，且部分使用了redis缓存。

### ✨ [体验地址(Click Me!)](http://order.itbear666.top:82/)

**推进使用手机浏览器访问，使用电脑浏览器请打开开发者工具，选择手机视图。**

客户端: http://order.itbear666.top:82/

商家端: http://order.itbear666.top:83/

# 整体效果

## 客户端

### 首页与用户登录

首页作为APP，展示了部分商家与美食关键词搜索。

<img src="https://shu-silence.oss-cn-shanghai.aliyuncs.com/img/2022/20220602000059.png" width=500px />

## 餐厅搜索

搜索时支持多条件排序，并显示历史搜索记录。

<img src="https://shu-silence.oss-cn-shanghai.aliyuncs.com/img/2022/20220602085418.png" width=500px />

## 点餐评论

商家内提供点餐操作，并没有做真实的支付功能。点餐成功后该商家人均价格与销售量会改变。

<img src="https://shu-silence.oss-cn-shanghai.aliyuncs.com/img/2022/20220602081352.png" width=500px />

订单评论，支持多图上传评价与基本的打分功能。

<img src="https://shu-silence.oss-cn-shanghai.aliyuncs.com/img/2022/20220602081937.png" width=500px />

订单完成后，可以在最近常买中快捷定位商家。

<img src="https://shu-silence.oss-cn-shanghai.aliyuncs.com/img/2022/20220602082152.png" width=500px />





## 扫码取餐

客户使用二维码即可在商家端部分扫码核销

<img src="https://shu-silence.oss-cn-shanghai.aliyuncs.com/img/2022/20220602081155.png" width=500px />



## 商家端

### 库存修改

商家可以查看商店内的商品，获取每件商品的销量与库存，并且可以商家，调整价格等

<img src="https://shu-silence.oss-cn-shanghai.aliyuncs.com/img/2022/20220602082949.png" width=500px />

修改商家营业状态，核销用户点餐二维码

<img src="https://shu-silence.oss-cn-shanghai.aliyuncs.com/img/2022/20220602083816.png" width=500px />

## 技术选型

#### 后端

| 技术        | 说明             |
| ----------- | ---------------- |
| SpringBoot  | 容器+MVC框架     |
| MyBatis     | ORM框架          |
| MyBatisPlus | 数据层代码生成   |
| Nginx       | 静态资源服务器   |
| Druid       | 数据库连接池     |
| Lombok      | 简化对象封装工具 |
| Redis       | 缓存             |
| Hutools     | 工具类           |

#### 前端

| 技术      | 说明       |
| --------- | ---------- |
| Uniapp    | 前端框架   |
| HBuilderX | 开发工具   |
| Uview2.0  | 前端UI框架 |
| vuex      | 全局总线   |
| Vue       | 前端框架   |


## 目录结构

- 前端部分：

```
|-- CloudOrdering_frontend
    |-- App.vue -- 主页面
    |-- main.js -- 整体配置
    |-- pages.json -- 页面配置
    |-- common -- 接口配置
    |-- static -- 静态资源
    |-- components -- 普通组件
    |-- pages -- 路由组件
    |-- store -- vuex配置
    |-- utils -- 工具包
```

- 后端部分：

```
|-- CloudOrdering_backend
	|-- config -- 各种配置
	|-- constants -- 常量
    |-- controller -- controller层以及SpringBoot配置
    |-- interceptor -- 拦截器等配置
    |-- mapper -- DAO层
    |-- model -- 数据库表中映射的JavaBean以及返回的Vo对象
    |-- service -- Service层
    |-- utils -- 工具类及通用代码
```


## 🚀 项目启动

1. 给此项目一颗⭐️
2. 前端启动
   - 自动下载相应的依赖 `npm install `
   - 启动前端项目，默认端口8080 `npm run serve`
3. 数据库部分
   - 执行项目路径下`/CloudOrdering_db/CloudOrdering.sql`脚本文件
   - 修改项目路径下`/CloudOrdering_backend/src/main/resources/application.yml`中的数据库配置

4. 后端启动
   - 使用IDE自动导入相应的Jar包
   - 启动后端项目，默认端口号8849，可以在项目路径下`/CloudOrdering_backend/src/main/resources/application.yml`修改配置

## 作者

👤 **胡才郁**

- Github: [@SHU-Silence](https://github.com/SHU-Silence)

👤 **张俊雄**

- Github: [@itbear-shu](https://github.com/itbear-shu)

👤 **刘元**

- Github: [@Liuyuan](https://github.com/LiuYuan-SHU)

👤 **杨家靖**

- Github: [@y_oung](https://gitee.com/y_oung)

