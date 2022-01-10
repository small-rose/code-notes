---
layout: default
title: 统一电子印章平台开发日志
nav_order: 10
---

# 国家标准

## 标准目录

- [国家政务服务平台标准 统一电子印章 总体技术架构](https://kdocs.cn/l/suU0CDfFCCAg)
  - [信息安全技术 SM3密码杂凑算法](http://c.gb688.cn/bzgk/gb/showGb?type=online&hcno=45B1A67F20F3BF339211C391E9278F5E)
  - [信息安全技术 SM2椭圆曲线公钥密码算法 第1部分：总则](http://c.gb688.cn/bzgk/gb/showGb?type=online&hcno=3EE2FD47B962578070541ED468497C5B)
  - [信息安全技术 SM2椭圆曲线公钥密码算法 第2部分：数字签名算法](http://c.gb688.cn/bzgk/gb/showGb?type=online&hcno=6F1FAEB62F9668F25F38E0BF0291D4AC)
  - [信息安全技术 SM2椭圆曲线公钥密码算法 第3部分：密钥交换协议](http://c.gb688.cn/bzgk/gb/showGb?type=online&hcno=66A89DD6DA64F49C49456B757BA0624F)
  - [信息安全技术 SM2椭圆曲线公钥密码算法 第4部分：公钥加密算法](http://c.gb688.cn/bzgk/gb/showGb?type=online&hcno=370AF152CB5CA4A377EB4D1B21DECAE0)
  - [信息安全技术 SM2椭圆曲线公钥密码算法 第5部分：参数定义](http://c.gb688.cn/bzgk/gb/showGb?type=online&hcno=728DEA8B8BB32ACFB6EF4BF449BC3077)
  - [信息安全技术 智能密码钥匙应用接口规范](http://c.gb688.cn/bzgk/gb/showGb?type=online&hcno=F969F4765DFEE1CD475201A2D55B10ED)
  - [信息安全技术 密码设备应用接口规范](http://c.gb688.cn/bzgk/gb/showGb?type=online&hcno=69E793FE1769D120C82F78447802E14F)
- [国家政务服务平台标准 统一电子印章 签章技术要求](https://kdocs.cn/l/sfmLMcesijo6)
  - [信息安全技术 密码应用标识规范](http://c.gb688.cn/bzgk/gb/showGb?type=online&hcno=252CF0F72A7BE339A56DEA7D774E8994)
  - [信息安全技术 公钥基础设施 时间戳规范](http://c.gb688.cn/bzgk/gb/showGb?type=online&hcno=D7A78565FC031B07389DCC44343A1727)
  - [信息安全技术 SM2密码算法使用规范](http://c.gb688.cn/bzgk/gb/showGb?type=online&hcno=2127A9F19CB5D7F20D17D334ECA63EE5)
  - [信息安全技术 SM3密码杂凑算法](http://c.gb688.cn/bzgk/gb/showGb?type=online&hcno=45B1A67F20F3BF339211C391E9278F5E)
  - [党政机关电子公文格式规范 第3部分：实施指南](http://c.gb688.cn/bzgk/gb/showGb?type=online&hcno=3904ECBBC695B9FA60FE3FB2B0CB9BF0)
  - [信息安全技术 安全电子签章密码技术规范](http://c.gb688.cn/bzgk/gb/showGb?type=online&hcno=EBF1360C272E40E7A8B9B32ED0724AB1)
- [国家政务服务平台标准 统一电子印章 印章技术要求](https://kdocs.cn/l/smZkhtPiS9ih)
  - [信息安全技术 公钥基础设施 数字证书格式](http://c.gb688.cn/bzgk/gb/showGb?type=online&hcno=F7B410A1B0C06206E5FFB0FBFEE82C75)
  - [党政机关电子印章应用规范](http://c.gb688.cn/bzgk/gb/showGb?type=online&hcno=375141AED78EB88C73461AFAAE4F746E)
  - [信息安全技术 SM2密码算法加密签名消息语法规范](http://c.gb688.cn/bzgk/gb/showGb?type=online&hcno=A7B91213CC4862B31BE2C84665CB8F7E)
- [国家政务服务平台标准 统一电子印章 接入测试方法](https://kdocs.cn/l/stX4qIeJiwh0)
- [国家政务服务平台标准 统一电子印章 系统接口要求](https://kdocs.cn/l/sa6mztgpiz8U)


## 系统架构

![](http://edrawcloudpubliccn.oss-cn-shenzhen.aliyuncs.com/viewer/self/1059758/share/2021-12-28/1640685770/main.svg)

# 具体实现

## 统一电子印章系统

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/202112281915554.png)

`绿建统一电子印章系统`和`国家政务服务平台统一电子印章系统`是平级关系

`绿建统一电子印章系统`和`各地区电子印章管理系统`属于一对多关系。

`绿建统一电子印章系统`为`各地区电子印章管理系统`颁发数字证书，用印章根签发。

### 功能模块

#### 用户管理模块

用户-角色-权限管理

主要有`电子印章系统管理员`,`电子印章制作管理员`,`电子印章发布管理员`等。

#### 电子印章管理模块

- `电子印章制作主体`的注册与管理
- `电子印章状态发布系统`的注册与管理
- `电子印章制作系统`的注册与管理

### 业务场景

## 各区域电子印章系统

### 电子印章制作系统

#### 相关接口



### 电子印章发布系统

## 电子印章应用系统

### 用章管理模块

### 验章模块

