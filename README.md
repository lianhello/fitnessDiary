# fitnessDiary

# 开发文档

## 开发环境

- jdk 1.8
- Mysql 5.7.19
- Redis 3.2
- 小程序基础库 2.10.2


## 技术选型

### 后端技术

- SpringBoot
- Mybatis
- Mysql
- Redis
- Maven

### 前端技术

- Vant Weapp
- Wemark



## 包管理

### 服务端

```txt
├─pom.xml 								        maven配置文件
│      
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─by
│  │  │          │  Application.java 	主程序入口
│  │  │          │  
│  │  │          ├─config				  配置类
│  │  │          │      
│  │  │          ├─controller			controller层
│  │  │          │      
│  │  │          ├─entity				  实体类
│  │  │          │      
│  │  │          ├─mapper				  dao层
│  │  │          │      
│  │  │          ├─service				service层
│  │  │          │  │  
│  │  │          │  └─impl				实现类
│  │  │          │          
│  │  │          └─util					  工具类
│  │  │                      
│  │  └─resources
│  │          application.properties	配置文件
│  │          
│  └─test
│      ├─java
│      │  └─com
│      │      └─test					测试类
│      │              
│      └─resources
```







# 开发日志

## 第三周（3/21-3/27）

- 公告栏：完成
  - 添加markdown渲染组件wemark
- 热量计算：完成度90%
  - 重做类型选择UI
  - 搜索功能完成
  - 优化加载页面方式，以防渲染页面时可能导致小程序内存不足崩溃
  - 菜单栏功能完成（提交按钮未完成）




## 第二周（3/14-3-20）

- 登录：完成度40%
  - 后台新增redis支持





## 第一周（3/9-3/13）

- 首页

  - 热量计算：完成度50%

    数据库设计完成，后台完成，前台页面未完成

- 个人页面：

  前台页面完成

  - 联系客服：完成度50%

