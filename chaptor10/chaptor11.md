# 在Spring中集成hibernate

简单的场景下，jdbc足够满足我们的需求，但是大部分实际应用中，我们需要许多额外的需求，jdbc对于这些应用来说就太过简陋
了，比如我们可能需要支持如下几种场景：
- 延迟加载
- 预先抓取
- 级联变动

一些可用的框架提供了这样的服务，在持久层使用orm框架，可以节省大量的代码量和开发时间。
Spring卫许多持久性框架提供了支持，包括Hibernate，iBatis，JDO，JPA等。与Spring对jdbc的支持一样，
Spring对Orm框架的支持提供了与这些框架的集成电路以及一些附加的服务：
- 支持集成Spring 声明式事务
- 透明的异常处理
- 线程安全的、轻量级的模板类
- dao支持类
- 资源管理

## 11.1 在Spring中集成Hibernate

Hibernate 是在开发者社区很流行的orm框架，他不仅提供了最基础的对象关系映射，还提供了orm工具所应有的所有复杂功能,biru 
缓存、延迟加载、预先抓取、分布式缓存等。

### 11.1.1 声明Hibernate的Session工厂

使用Hibernate的主要接口是org.hibernate.Session。session接口提供了基本的数据访问功能，
如保存更新等。
在Spring中，我们需要通过Spring的某一个Hibernate Session工厂bean来获取Hibernate SessionFactory，共有三种
Session工厂供我们选择：
- org.springframework.orm.hibernate3.LocalSessionFactoryBean
- org.springframwork.orm.hibernate3.annocation.AnnocationSessionFactoryBean
- org.springframework.orm.hibernate4.LocalSessionFactoryBean

这些SessionFactoryBean都是Spring FactoryBean的接口实现，他们会产生一个Hibernate的SessionFactory，能够装备金任何SessionFactory类型的属性中去。
使用哪种Bean工厂，取决于你的Hibernate版本，4以下使用xml配置则使用hibernate3的LocalSessionFactoryBean，使用注解
配置则使用AnnocationSessionFactoryBean，
4.0以上则使用hibernate4的LocalSessionFactoryBean
见HibernateDataSource

### 11.1.2 构建不依赖于Spring的Hibernate代码

## 11.2 Spring 与java持久化API
Jpa是基于POJO的持久化机制，他从Hibernate和JDO借鉴了很多理念，并加入了注解的特性

### 11.2.1 配置实体管理器工厂
简单来说，基于JPA的应用程序需要使用EntityManagerFactory的实现类来获取EntityManager实例。
JPA定义了两种类型的实体管理器：
- 应用程序管理类型：当应用程序向EntityManagerFactory直接请求实体管理器时，
工厂会创建一个实体管理器，在这种模式下，程序要负责打开或关闭实体管理器并在事务中对其进行控制，这种方式的EntityManager适合不在java EE容器中的独立应用程序

- 容器管理类型：实体管理器由JavaEE创建和管理，应用程序根本不与实体管理器工厂打交道，相反，实体管理器直接通过注入或JNDI获取，容器负责配置EntityManagerFactory

而在Spring的场景下，对于用户来说是无感知的，两种实体类工厂均由Spring来创建
- LocalEntityManagerFactoryBean 生成应用程序管理类型的EntityManagerFactory
- LocalContainerEntityManagerFacytoryBean 生成容器类型的工厂

#### 配置应用程序管理类型的JPA



#### 使用容器管理类型的JPA

#### 从JNDI中获取实体类管理器工厂

### 11.2.2 编写基于JPA的Repository

## 11.3 借助Spring Data 实现自动化的JPA Repository
