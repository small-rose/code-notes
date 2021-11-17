---
layout: docs
title: 陆国松的编程笔记
---
# 网站构建技术选型

- 网站采用github pages搭建，主要涉及以下技术：
  - [github pages](https://docs.github.com/cn/pages)
  - [jekyll](https://jekyllrb.com/)
  - [liquid](https://github.com/Shopify/liquid/wiki)[中文文档](https://liquid.bootcss.com/)
  - jekyll主题：[just-the-docs](https://github.com/pmarsceill/just-the-docs)
  - 博客评论功能：[Gitalk](https://github.com/gitalk/gitalk)
  - 页面目录：[tocbot](https://github.com/tscanlin/tocbot)
  - 网站图片：[github图床](https://github.com/guosonglu/images)+[jsdelivr加速](https://www.jsdelivr.com/github)+[PicGo工具](https://github.com/Molunerfinn/PicGo)
  - 图片查看：[Viewer.js](https://github.com/fengyuanchen/viewerjs)
  - 数学公式排版：[LaTeX](https://www.latex-project.org/)[MathJax](http://docs.mathjax.org/en/latest/)
  - 流程图： [mermaid](https://mermaid-js.github.io/mermaid/#/)
  - 网站访问计数：[不蒜子](http://busuanzi.ibruce.info/)[百度统计](https://tongji.baidu.com/web/10000396058/welcome/login)

# 项目目录结构

[项目地址](https://github.com/guosonglu/code-notes)

| 目录或文件       | 说明                                         |
| :--------------- | :------------------------------------------- |
| .idea            | 项目的配置信息，包括历史记录，版本控制信息等 |
| _c               | c语言相关项目                                |
| _environment     | 环境和工具说明文档                           |
| _foreignlanguage | 外语笔记                                     |
| _front           | 前端笔记                                     |
| _general         | 计算机基础知识                               |
| _includes        | 网站公共html                                 |
| _java            | Java相关项目                                 |
| _layouts         | 网站布局                                     |
| _math            | 数学相关笔记                                 |
| _sass            | 样式文件                                     |
| assets           | 网站资源目录                                 |
| .gitignore       | Git忽略文件                                  |
| 404.html         | 404页面                                      |
| _config.yml      | jekyll配置文件，全局设置                     |
| CNAME            | github pages域名解析                         |
| favicon.ico      | 网站图标                                     |
| Gemfile          | 描述gem之间依赖                              |
| Gemfile.lock     | Bundler记录已经安装了的版本                  |
| index.md         | 网站首页                                     |
| pom.xml          | 整个项目基于Maven，这个pom做全局版本管理     |
| README.md        | github首页说明                               |

# 网站性能优化

由于网站是使用github pages部署的，国内访问相对很慢，做如下优化提高访问速度：

- 网站中的静态资源（图片、js、css）使用[jsdelivr](https://www.jsdelivr.com/github)加速
- 网站使用[cloudflare](https://dash.cloudflare.com/)做CDN加速
- Html页面头部添加 `<meta http-equiv="Cache-Control" content="max-age=7200" />`，防止每次刷新页面都反复获取静态文件

# 编程史

![](https://edrawcloudpubliccn.oss-cn-shenzhen.aliyuncs.com/viewer/self/1059758/share/2021-7-30/1627611901/main.svg)
