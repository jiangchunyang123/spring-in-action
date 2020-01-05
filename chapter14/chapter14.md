# 14 保护方法应用

在web应用中，安全问题是非常高频出现的，Spring提供了一些保护bean方法的注解

## 14.1 使用注解保护方法
在SPringSecurity中实现方法级别的安全的最常见方法就是使用特定的注解，将这些注解
应用到需要保护的方法上，Spring Security提供了三种不同的安全注解
- Spring Security自带的@Security注解
- JSR-250的@RoleAllowed注解
- 表达式驱动的注解，包括@PreAuthorize，@PostAuthorize，@PreFilter和@PostFilter

### 14.1.1 使用@Secured 注解限制方法调用

在Spring Security中，启用注解需要使用@EnableGlobalMethodSecurity注解

### 14.1.2 在Spring Security中使用JSR-250 的@RolesAloowed注解



## 14.2 使用表达式实现方法级别的安全性
尽管@Secured和@RolesAllowed注解在拒绝未认证用户方面表现不错，但这也是他们能做的
所有事情了。有时候，安全性约束不仅仅涉及用户是否拥有权限。
Spring Security3.0引入了几个新的注解，他们使用SpEl能够在方法上实现更有意思的注解

|注解|描述|
|---|---|
|@PreAuthorize|方法调用前，根据表达式的计算结果来保护方法调用|
|@PostAuthorize|允许方法调用，但是如果表达式计算结果为false，将抛出一个安全性异常|
|@PreFilter和|允许方法调用，但必须按照表达式来过滤方法的结果|
|@PostFilter|允许方法调用，但必须在进入方法之前过滤输入值|

### 14.2.1 表述方法访问规则

#### 在方法调用前验证权限

#### 在方法调用后验证权限

### 14.2.2 过滤方法的输入和输出

#### 事后对方法的返回值进行过滤

#### 事先对方法的参数进行过滤

#### 定义许可计算器
