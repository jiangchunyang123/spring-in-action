<!-- TOC -->

- [13 缓存数据](#13-缓存数据)
    - [13.1 启用对缓存的支持](#131-启用对缓存的支持)
        - [13.1.1 配置缓存管理器](#1311-配置缓存管理器)
            - [使用Ehcache缓存](#使用ehcache缓存)
            - [使用Redis缓存](#使用redis缓存)
            - [使用多个缓存管理器](#使用多个缓存管理器)
    - [13.2 为方法添加注解以支持缓存](#132-为方法添加注解以支持缓存)
        - [13.2.1 填充缓存](#1321-填充缓存)
            - [将值放到缓存之中](#将值放到缓存之中)
            - [自定义缓存key](#自定义缓存key)
            - [条件化缓存](#条件化缓存)
        - [13.2.2 移除缓存条目](#1322-移除缓存条目)
    - [13.3 使用XML声明缓存](#133-使用xml声明缓存)

<!-- /TOC -->
# 13 缓存数据

有些高频数据变动很小，或者根本不会变动，频繁访问数据库的话会对数据库性能造成较大压力，
这时就可以启用缓存来存储这些数据，提升查询速度，降低数据库压力。

## 13.1 启用对缓存的支持

Spring对缓存的支持，有两种方式：
- 注解驱动的缓存
- XML声明的缓存

使用Spring的缓存抽象最为通用的方式就是在方法上添加@Cacheable和@CacheEvict注解。
在往bean上面增加Cacheable注解之前，首先要启用注解。
需要在配置类上增加@EnableCaching注解。

### 13.1.1 配置缓存管理器

在配置@EnableCaching之后，需要配置一个缓存管理器CacheManager，在例子中
就是一个使用本地ConcurrentHashMap作为注解的管理器实现。
Spring3.1 内置了5个缓存管理器实现
- SimpleCacheManager
- NoOpCacheManager
- ConcurrentHashMapCacheManager
- CompositeCacheManager
- EhCacheCacheManager

Spring 3.2 引入了另一个缓存管理器，这个管理器可以使用在给予JCache（JSR-107）的
缓存提供商之中。除了核心的Spring框架，SpringData 有提供了两个缓存管理器：
- RedisCacheManager （来自于Spring Data Redis项目）
- GemfireCacheManager （来自于Spring Data GemFire项目）

下面我们开始配置一下其他的Cachemanager

#### 使用Ehcache缓存 

Ehcache是最流行的缓存提供商之一，缓存名称即EhcacheCacheManager

#### 使用Redis缓存

很自然的，我们会想到redis可以做为第三方缓存管理器，集成到Spring当中

#### 使用多个缓存管理器

可以使用CompositeCacheManager配置缓存


## 13.2 为方法添加注解以支持缓存

如前文所述，Spring的缓存抽象在很大程度上是围绕切面构建的。在Spring中启动缓存时，会创建一个切面，他出发一个或者
更多的Spring缓存注解，下表列出了Spring所提供的缓存注解

|注解|描述|
|---|---|
|@Cacheable|表明Spring在调用方法前，会现在缓存中查找方法的返回值，如果这个值能够找到就会返回缓存的值，否则这个方法就会被调用|
|@CachePut|表明Spring应该将方法的返回值放到缓存中，在方法的调用前并不会检查缓存，方法始终会被调用|
|@CacheEvict|表明Spring应该在缓存中清除一个或者多个条目|
|@Caching|这是一个分组的注解，能够同时应用多个其他的缓存注解|

### 13.2.1 填充缓存

@Cacheable @Cacheput两种注解可以实现两种方式的缓存存储策略，

#### 将值放到缓存之中

@CachePut注解，可以在save时将返回值塞到缓存中，然而key仍然是一个对象，这时间
很诡异的事情，所以我们需要自定义缓存

#### 自定义缓存key
@Cacheable @Cacheput两种注解的key属性能够替换默认的key，他是一个SpEL表达式  
Spring为编写El表达式暴露了一些很有用的元数据

|表达式|描述|
|---|---|
|#root.args|传递给缓存方法的参数，形式为数组|
|#root.caches|该方法执行时所对应的缓存，形式维数组|
|root.target|目标对象|
|root.targetClass|目标类|
|root.method|目标方法|
|root.methodName|目标方法名|
|result|方法调用的返回值（不能用在Cacheable注解上）|
|Argument|任意的方法参数名，如#argName或者参数索引#a0|

#### 条件化缓存

某种情况下，我们希望将缓存关闭，两种注解都提供了两个属性以实现条件化缓存，unless和condition
这两个属性同样可以接受SPEL表达式

### 13.2.2 移除缓存条目

@CacheEvict 注解在方法上可以移除某个缓存，

## 13.3 使用XML声明缓存
xml形式的就是配置了一个切面，然后注入到自定义的cache元素中，其他类似，不再赘述