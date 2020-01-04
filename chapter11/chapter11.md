
# 1. 使用对象关系映射持久化数据
<!-- TOC -->

- [1. 使用对象关系映射持久化数据](#1-使用对象关系映射持久化数据)
    - [1.1. 前言](#11-前言)
    - [1.2. 在Spring中集成Hibernate](#12-在spring中集成hibernate)
        - [1.2.1. 声明Hibernate的Session工厂](#121-声明hibernate的session工厂)
        - [1.2.2. 构建不依赖于Spring的Hibernate代码](#122-构建不依赖于spring的hibernate代码)
    - [1.3. Spring 与java持久化API](#13-spring-与java持久化api)
        - [1.3.1. 配置实体管理器工厂](#131-配置实体管理器工厂)
            - [1.3.1.1. 配置应用程序管理类型的JPA](#1311-配置应用程序管理类型的jpa)
            - [1.3.1.2. 使用容器管理类型的JPA](#1312-使用容器管理类型的jpa)
            - [1.3.1.3. 从JNDI中获取实体类管理器工厂](#1313-从jndi中获取实体类管理器工厂)
        - [1.3.2. 编写基于JPA的Repository](#132-编写基于jpa的repository)
    - [1.4. 借助Spring Data 实现自动化的JPA Repository](#14-借助spring-data-实现自动化的jpa-repository)
        - [1.4.1. 定义查询方法](#141-定义查询方法)
        - [1.4.2. 声明自定义查询](#142-声明自定义查询)
        - [1.4.3. 混合自定义功能](#143-混合自定义功能)

<!-- /TOC -->

## 1.1. 前言

简单的场景下，jdbc足够满足我们的需求，但是大部分实际应用中，我们需要许多额外的需求，jdbc对于这些应用来说就太过简陋
了，比如我们可能需要支持如下几种场景：

- 延迟加载
- 预先抓取
- 级联

一些可用的框架提供了这样的服务，在持久层使用orm框架，可以节省大量的代码量和开发时间。
Spring卫许多持久性框架提供了支持，包括Hibernate，iBatis，JDO，JPA等。与Spring对jdbc的支持一样，
Spring对Orm框架的支持提供了与这些框架的集成点以及一些附加的服务：

- 支持集成Spring 声明式事务
- 透明的异常处理
- 线程安全的、轻量级的模板类
- DAO支持类
- 资源管理

## 1.2. 在Spring中集成Hibernate

Hibernate 是在开发者社区很流行的orm框架，他不仅提供了最基础的对象关系映射，还提供了orm工具所应有的所有复杂功能,比如缓存、延迟加载、预先抓取、分布式缓存等。

### 1.2.1. 声明Hibernate的Session工厂

使用Hibernate的主要接口是org.hibernate.Session。session接口提供了基本的数据访问功能，如保存更新等。
在Spring中，我们需要通过Spring的某一个Hibernate Session工厂bean来获取Hibernate SessionFactory，共有三种
Session工厂供我们选择：

- org.springframework.orm.hibernate3.LocalSessionFactoryBean
- org.springframwork.orm.hibernate3.annocation.AnnocationSessionFactoryBean
- org.springframework.orm.hibernate4.LocalSessionFactoryBean

这些SessionFactoryBean都是Spring FactoryBean的接口实现，他们会产生一个Hibernate的SessionFactory，能够装备到任何SessionFactory类型的属性中去。  
使用哪种Bean工厂，取决于你的Hibernate版本，4以下使用xml配置则使用hibernate3的LocalSessionFactoryBean，使用注解配置则使用AnnocationSessionFactoryBean，
4.0以上则使用hibernate4的LocalSessionFactoryBean


### 1.2.2. 构建不依赖于Spring的Hibernate代码
在Spring和Hibernate的早期岁月中，编写Repository类会使用到SPring的HibernateTemplate。HibernateTemplate会保证每个事务
使用同一个Session。但是这种方式的的弊端在于我们的Repository实现会直接与Spring耦合，现在的最佳实践时使用上下文Session，直接将SessionFactory注入到我们的代码中，
并用它来获取session，实现独立的Hibernate代码。

## 1.3. Spring 与java持久化API

java持久化api诞生在EJB实体bean的废墟之上，斌成为了下一代Java持久化标准。
Jpa是基于POJO的持久化机制，他从Hibernate和JDO借鉴了很多理念，并加入了注解的特性
在Spring中使用JPA的第一步是要在Spring应用上下文中将实体管理器工厂按照bean的形式来配置

### 1.3.1. 配置实体管理器工厂

简单来说，基于JPA的应用程序需要使用EntityManagerFactory的实现类来获取EntityManager实例。
JPA定义了两种类型的实体管理器：

- 应用程序管理类型：当应用程序向EntityManagerFactory直接请求实体管理器时，
工厂会创建一个实体管理器，在这种模式下，程序要负责打开或关闭实体管理器并在事务中对其进行控制，
这种方式的EntityManager适合不在java EE容器中的独立应用程序

- 容器管理类型：实体管理器由JavaEE创建和管理，应用程序根本不与实体管理器工厂打交道，
相反，实体管理器直接通过注入或JNDI获取，容器负责配置EntityManagerFactory

而在Spring的场景下，对于用户来说是无感知的，两种实体类工厂均由Spring来创建

- LocalEntityManagerFactoryBean 生成应用程序管理类型的EntityManagerFactory

- LocalContainerEntityManagerFacytoryBean 生成容器类型的工厂

#### 1.3.1.1. 配置应用程序管理类型的JPA

对于应用程序管理类型的实体管理器工厂来说，他的绝大部分配置信息来自于一个名为persistence.xml的配置文件，这个文件必须位于类路径的META-INF目录下。
persistence。xml的作用在于定义一个或者多个持久化单元，但是借助于Spring 对JPA的支持，我们不需要再把配置放在persistence.xml中了，看一下
容器管理的JPA。

#### 1.3.1.2. 使用容器管理类型的JPA

对于容器管理的JPA，可以将Spring自己的数据源注入进去，而不是只能配置在persistence.xml中了，实例见代码

#### 1.3.1.3. 从JNDI中获取实体类管理器工厂

略

### 1.3.2. 编写基于JPA的Repository
正如Spring对其他持久化方案的集成一样，Spring对JPA的集成也提供了JPATemplate的模板类及对应的支持类JpaDaoSupport，
但是为了更纯粹的Jpa方式，模板的方式已经被启用了。
本节会重点关注如何不依赖Spring实现Jpa

## 1.4. 借助Spring Data 实现自动化的JPA Repository
尽管之前的代码已经不是很复杂了，但是他们依然会直接与EntityManager进行交互，来查询数据库，
并且仔细观察的话，这些代码多少还是样板类的。很多地方仍然在重复一些代码。
Spring Data jpa就是来消除这种样板式代码的，只需要编写接口，不需要写实现类

### 1.4.1. 定义查询方法

Spring Data Jpa 可以通过集成某个Repository接口，来自动生成开箱即用的几个方法，
同时也支持根据某种方法命名规则自定义查询方法，从而让Jpa自动生成实现

### 1.4.2. 声明自定义查询

在Repository方法上使用@query注解，可以使用类sql和sql语句实现想要的功能

### 1.4.3. 混合自定义功能

同时可以自己实现相关Repository实现类，注入原始的EntityManager来混合实现

