# 第12章 使用NoSql数据库
关系型数据库垄断企业应用已经很多年了，但是关系型数据库并不是最适用于所有业务场景
随着一些竞争者进入数据库领域，关系型数据库的垄断地位开始被弱化。
所谓的NoSql数据库开始进入数据库领域，我们也意识到了没有一种全能的数据库。
Spring Data 还提供了对多种NoSql数据库的支持，包括MongoDB,Neo4j和 Redis等。

## 12.1 使用MongoDb持久化文档数据

有些数据的最佳表达形式是文档，这时可以使用MongoDB来存储数据  
MongoDB与Spring集成方式主要有三种

- 通过注解实现对象-文档映射
- 使用MongoTemplate实现基于模板的数据库访问
- 自动化的运行时Repository生成功能

### 12.1.1 启用MongoDB

在com.learn.spiactn.mongo中，几个示例类

### 12.1.2 为模型添加注解实现持久化

### 12.1.3 使用MongoTemplate访问MongoDB

### 12.1.4 编写MongoDB Repository

## 12.2 使用Neo4j操作图数据

### 12.2.1 配置Spring Data Neo4j

### 12.2.2 使用注解标注图实体

### 12.2.3 使用Neo4jTemplate

### 12.2.4 创建自动化的Neo4j Repository

## 12.3 使用Redis操作Key-Value数据

### 12.3.1 连接到Redis

### 12.3.2 使用RedisTemplate

### 12.3.3 使用key和Value的序列化器
