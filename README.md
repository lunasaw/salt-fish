

# salt-fish

salt-fish

<!-- PROJECT SHIELDS -->

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- PROJECT LOGO -->
<br />

<p align="center">
  <a href="https://github.com/czy1024/salt-fish/">
    <img src="https://tva1.sinaimg.cn/large/0081Kckwgy1gl86jd3z44j30i00503yt.jpg" alt="Logo" width="240" height="80">
  </a>

  <h3 align="center">盐鱼</h3>
  <p align="center">
    盐鱼(校园二手交易平台)
    <br />
    <a href="https://czy1024.github.io/salt-fish"><strong>探索本项目的文档 »</strong></a>
    <br />
    <br />
    <a href="http://lunac.vaiwan.com/salt-fish/">演示地址</a>
    ·
    <a href="">报告Bug</a>
    ·
    <a href="https://github.com/czy1024/salt-fish/issues">提出新特性</a>
  </p>

</p>


 
## 目录

- [上手指南](#上手指南)
  - [开发前的配置要求](#开发前的配置要求)
  - [安装步骤](#安装步骤)
- [文件目录说明](#文件目录说明)
- [部署](#部署)
- [使用到的框架](#使用到的框架)
- [贡献者](#贡献者)
  - [如何参与开源项目](#如何参与开源项目)
- [作者](#作者)
- [鸣谢](#鸣谢)

### 上手指南


###### **安装步骤**

1. Clone the repo

```sh
git clone https://github.com/czy1024/salt-fish.git
```
### 部署

eg:

    当然首先你需要修改你的数据库@src/main/java/com/luna/saltfish/dbc/DatabaseConnection.java
    将sql文件夹下的数据导入你的数据库
    使用tomcat：项目开发使用的版本为8
    使用mysql版本为：5.7
    java版本为：jdk1.8
### 文件目录说明

```
____src
| |____main
| | |____resources
| | |____webapp
| | | |____error_404.jsp
| | | |____.DS_Store
| | | |____test
| | | | |____tips.txt
| | | |____about.jsp
| | | |____user
| | | | |____agreement.jsp
| | | | |____sendmess.jsp
| | | | |____personal.jsp
| | | | |____login.jsp
| | | | |____register.jsp
| | | |____META-INF
| | | | |____MANIFEST.MF
| | | |____static
| | | | |____goods_img
| | | | |____user_img
| | | | |____image
| | | |____index.jsp
| | | |____WEB-INF
| | | | |____lib
| | | | | |____javasqlxt.jar
| | | | |____web.xml
| | | |____site
| | | | |____personal
| | | | | |____history.jsp
| | | | | |____auditing.jsp
| | | | | |____pushed.jsp
| | | | | |____push.jsp
| | | | | |____shopcart.jsp
| | | | | |____info.jsp
| | | | | |____mess.jsp
| | | | | |____like.jsp
| | | | |____header.jsp
| | | | |____head.jsp
| | | | |____footer.jsp
| | | |____goods
| | | | |____info.jsp
| | | |____src
| | | | |____jquery2
| | | | | |____jquery.min.js
| | | | | |____jquery.js
| | | | |____css
| | | | |____bootstrap3
| | | | | |____css
| | | | | |____js
| | | | | | |____npm.js
| | | | | | |____bootstrap.js
| | | | | | |____bootstrap.min.js
| | | | | |____fonts
| | | |____search.jsp
| | |____java
| | | |____com
| | | | |____luna
| | | | | |____saltfish
| | | | | | |____vo
| | | | | | | |____Order.java
| | | | | | | |____Mess.java
| | | | | | | |____Goods.java
| | | | | | | |____User.java
| | | | | | |____servlet
| | | | | | | |____OrderCheckServlet.java
| | | | | | | |____ShoppingCartServlet.java
| | | | | | | |____CollectServlet.java
| | | | | | | |____AuditingServlet.java
| | | | | | | |____UpdateUserImgServlet.java
| | | | | | | |____RemoveShopCartServlet.java
| | | | | | | |____RegisterServlet.java
| | | | | | | |____CharSet.java
| | | | | | | |____BuyAllShopcartServlet.java
| | | | | | | |____MessCheckServlet.java
| | | | | | | |____AutoLogin.java
| | | | | | | |____RemoveCollectServlet.java
| | | | | | | |____UpdateUserInfoServlet.java
| | | | | | | |____RemoveMessServlet.java
| | | | | | | |____LoginServlet.java
| | | | | | | |____GoodsCheckServlet.java
| | | | | | | |____ExitLoginServlet.java
| | | | | | |____tools
| | | | | | | |____MD5.java
| | | | | | | |____GetCookie.java
| | | | | | | |____LoginVerify.java
| | | | | | | |____IntHolder.java
| | | | | | | |____StaticVar.java
| | | | | | |____dbc
| | | | | | | |____DatabaseConnection.java
| | | | | | |____dbHandle
| | | | | | | |____MessHandle.java
| | | | | | | |____UserHandle.java
| | | | | | | |____GoodsHandle.java
| | | | | | | |____OrderHandle.java
| | | | | | | |____CollectHandle.java
| | | | | | | |____ShopCartHandle.java
|____sql
| |____luna-shop.sql
```



### 开发的架构 

利用servlet+jsp

### 贡献者

请阅读**CONTRIBUTING.md** 查阅为该项目做出贡献的开发者。

#### 如何参与开源项目

贡献使开源社区成为一个学习、激励和创造的绝佳场所。你所作的任何贡献都是**非常感谢**的。


1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



### 版本控制

该项目使用Git进行版本管理。您可以在repository参看当前可用版本。

### 作者

luna

email Keyluna@126.com  &ensp; qq:1173288254

 *您也可以在贡献者名单中参看所有参与该项目的开发者。*

### 版权说明

该项目签署了MIT 授权许可，详情请参阅 [LICENSE.txt](https://github.com/czy1024/salt-fish/blob/master/LICENSE)

### 鸣谢[]()


- [ruoyi]()



<!-- links -->
[your-project-path]:czy1024/salt-fish
[contributors-shield]: https://img.shields.io/github/contributors/czy1024/salt-fish.svg?style=flat-square
[contributors-url]: https://github.com/czy1024/salt-fish/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/czy1024/salt-fish.svg?style=flat-square
[forks-url]: https://github.com/czy1024/salt-fish/network/members
[stars-shield]: https://img.shields.io/github/stars/czy1024/salt-fish.svg?style=flat-square
[stars-url]: https://github.com/czy1024/salt-fish/stargazers
[issues-shield]: https://img.shields.io/github/issues/czy1024/salt-fish.svg?style=flat-square
[issues-url]: https://img.shields.io/github/issues/czy1024/salt-fish.svg
[license-shield]: https://img.shields.io/github/license/czy1024/salt-fish.svg?style=flat-square
[license-url]: https://github.com/czy1024/salt-fish/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/salt-fish




