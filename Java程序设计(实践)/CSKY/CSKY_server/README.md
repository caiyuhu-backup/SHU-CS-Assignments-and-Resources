<h1 align="center">Welcome to CSKY 👋</h1>

[<img src="https://gitee.com/SHU-Silence/drawing-bed/raw/master/img/logo.png" alt="logo"  />](http://47.100.249.168:8989/#/home)




> 2021-2022 学年冬季学期《Java程序设计(实践)》大作业 --->>> **CSKY (CS考研，计算机考研网)**

### 🏠 [项目主页](https://github.com/SHU-Silence/CSKY)

本项目为前后端分离项目，前端采用Vue框架，UI采用element-UI,后端采用Springboot整合SSM，并采用Mybatis Plus简化CRUD操作。数据部分通过爬取获得。

### ✨ [演示地址(Click Me!)](http://47.100.249.168:8989/#/home)

- 账号：tourist
- 密码：123456

# 整体效果

## 首页

作为展示页面，展示了招生公告、研招新闻、报考指南、研招答疑、考研大纲等文章简略信息。

![image-20220301213357080](https://gitee.com/SHU-Silence/drawing-bed/raw/master/img/image-20220301213357080.png)

## 用户注册登陆界面

注册时提供新用户邮箱，接收邮件之中的验证码，完成注册。

![image-20220301213443416](https://gitee.com/SHU-Silence/drawing-bed/raw/master/img/image-20220301213443416.png)

## 34所高校

提供了34所985高校的简介，支持关键词搜索，并可以根据每一个学校的简介，进入查看每一个学校的详情信息

1. 招考信息
2. 近三年分数线信息
3. 师资介绍

## 考研时间线

![image-20220301213519919](https://gitee.com/SHU-Silence/drawing-bed/raw/master/img/image-20220301213519919.png)

## 考研分数线

展示了34所985高校计算机相关专业近三年(21、20、19)的考研复试分数线，并且支持分数排序、分页展示等功能

## 真题下载

提供了考研四门科目的近20年真题下载方式

由于数据有限，只收集到以下科目题目

- 数学一
- 英语一/二
- 政治
- 408

912、857等较为小众的试题资源没有获得(作者找不到资源...) (┭┮﹏┭┮)

## 帖子发表

支持Markdown格式的编写，在文章之中插入图片等，并且为文章添加相应的标签。文章发表后,文章中使用到的本地图片自动上传至七牛云图床。

![image-20220301213607309](https://gitee.com/SHU-Silence/drawing-bed/raw/master/img/image-20220301213607309.png)

![image-20220301213754780](https://gitee.com/SHU-Silence/drawing-bed/raw/master/img/image-20220301213754780.png)

## 技术选型

#### 前端技术

| 技术        | 说明             |
| ----------- | ---------------- |
| SpringBoot  | 容器+MVC框架     |
| MyBatis     | ORM框架          |
| MyBatisPlus | 数据层代码生成   |
| Nginx       | 静态资源服务器   |
| Druid       | 数据库连接池     |
| JWT         | JWT登录支持      |
| Lombok      | 简化对象封装工具 |

#### 后端技术

| 技术       | 说明             |
| ---------- | ---------------- |
| Vue        | 前端框架         |
| Vue-router | 路由框架         |
| Vuex       | 全局状态管理框架 |
| Element    | 前端UI框架       |
| Axios      | 前端HTTP框架     |
| nprogress  | 进度条控件       |

## 目录结构

- 前端部分：

```
|-- CSKY_Client
    |-- src
        |-- App.vue -- 主页面
        |-- main.js -- 整体配置
        |-- api -- 接口配置
        |-- assets -- 静态资源
        |-- components -- 普通组件
        |-- pages -- 路由组件
        |-- routers -- 路由配置
        |-- store -- vuex配置
        |-- utils -- 工具包
```

- 后端部分：

```
|-- CSKY_Server
    |-- api -- controller层以及SpringBoot配置
    |-- beans -- 数据库表中映射的JavaBean以及返回的Vo对象
    |-- common -- 工具类及通用代码
    |-- mapper -- DAO层
    |-- service -- Service层
```

- 爬虫部分

```
|-- CSKY_Crawler
    |-- classes
    |	|-- Major				存储专业信息
    |	|-- School				存储学校信息
    |	|-- Teacher				存储教师信息
    |-- crawlers
    |	|-- getSchoolID			 获取学校ID（与网页URL对应）和学校名称
    |	|-- getCSInfo			 爬取公众号上学校的分数线（国家线）
    |	|-- ArticleOperating		  获取学校官方文章，保存URL
    |	|-- getTeacherInfo		  获取各个学校教师对应的网页URL
    |	|-- getTeacherDetail		  获取教师网页中教师的详细信息
    |-- ChangeInfo				 根据后期需求变化，对已有信息进行补充
```

## 🚀 项目启动

1. 给此项目一颗⭐️
2. 前端启动
   - 自动下载相应的依赖 `npm install `
   - 启动前端项目，默认端口8080 `npm run serve`
3. 数据库部分
   - 执行项目路径下`/DB/CSKY.sql`脚本文件
   - 修改项目路径下`/CSKY_Server/api/main/resources/application.yml`中的数据库配置

4. 后端启动
   - 使用IDE自动导入相应的Jar包
   - 启动后端项目，默认端口号80，可以在项目路径下`/CSKY_Server/api/main/resources/application.yml`修改配置
## 项目存在的问题

1. 后端代码可复用性很差，许多重复的操作没有写继承多态、各种Vo写了一大堆....
2. 由于查询时使用动态查询，若查找到，则记录查找的搜索词，会将一些奇奇怪怪的关键词存入数据库
3. 七牛云免费域名只提供三十天，由于考研真题、试题资源都存在七牛云上，所以到期后应该就访问不了了

