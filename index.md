---
layout: docs
---

# 图片显示问题

- 本网站大部分图片采用github作为图床，由于
  [防火长城](https://zh.wikipedia.org/wiki/%E9%98%B2%E7%81%AB%E9%95%BF%E5%9F%8E)
  对`raw.githubusercontent.com`域名做了
  [DNS污染](https://zh.wikipedia.org/wiki/%E5%9F%9F%E5%90%8D%E6%9C%8D%E5%8A%A1%E5%99%A8%E7%BC%93%E5%AD%98%E6%B1%A1%E6%9F%93)，如果国内用户无法正常查看图片，解决方案如下：
  - 方法一：科学上网，`中华人民共和国计算机信息网络国际联网管理暂行规定`中第六条规定计算机信息网络直接进行国际联网，必须使用邮电部国家公用电信网提供的国际出入口信道。`任何单位和个人不得自行建立或者使用其他信道进行国际联网`。所有不建议使用此方法
  - 方法二：本地hosts文件配置`raw.githubusercontent.com`域名指向对应IP，
    目前查到的ip是`185.199.110.133`,后面变了会更新
    ![](/assets/images/Snipaste_2021-09-18_17-03-50.png)

# 网站构建技术选型

- 网站采用github pages搭建，主要涉及以下技术：
  - [github pages](https://docs.github.com/cn/pages)
  - [jekyll](https://jekyllrb.com/)
  - [liquid](https://github.com/Shopify/liquid/wiki)
  - jekyll主题：[just-the-docs](https://github.com/pmarsceill/just-the-docs)
  - 博客评论功能：[Gitalk](https://github.com/gitalk/gitalk)
  - 页面目录：[jekyll-toc](https://github.com/allejo/jekyll-toc)
  - 图片查看：[Viewer.js](https://github.com/fengyuanchen/viewerjs)
  - 数学公式排版：[LaTeX](https://www.latex-project.org/) [MathJax](http://docs.mathjax.org/en/latest/)
  - 流程图： [mermaid](https://mermaid-js.github.io/mermaid/#/)

# 项目目录结构

[项目地址](https://github.com/guosonglu/code-notes)


| 目录或文件   | 说明                                     |
| :------------- | :----------------------------------------- |
| _c           | c语言相关项目                            |
| _environment | 环境和工具说明文档                       |
| _includes    | 网站公共html                             |
| _java        | Java相关项目                             |
| _layouts     | 网站布局                                 |
| _math        | 数学相关笔记                             |
| _sass        | 样式文件                                 |
| assets       | 网站资源目录                             |
| .gitignore   | Git忽略文件                              |
| 404.html     | 404页面                                  |
| _config.yml  | jekyll配置文件，全局设置                 |
| CNAME        | github pages域名解析                     |
| favicon.ico  | 网站图标                                 |
| Gemfile      | 描述gem之间依赖                          |
| Gemfile.lock | Bundler记录已经安装了的版本              |
| index.md     | 网站首页                                 |
| pom.xml      | 整个项目基于Maven，这个pom做全局版本管理 |
| README.md    | github首页说明                           |

# 编程史

![](https://edrawcloudpubliccn.oss-cn-shenzhen.aliyuncs.com/viewer/self/1059758/share/2021-7-30/1627611901/main.svg)
