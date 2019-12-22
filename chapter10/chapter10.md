# 10 通过spring和JDBC征服数据库

## 10.1 

## 10.2 配置数据源

无论选择哪种数据访问方式，都需要一个数据源的引用，spring提供了三种在上下文配置数据源bean的方式，包括：

- 通过JDBC驱动程序定义的数据源
- 通过JNDI查找的数据源
- 连接池的数据源

### 10.2.1 使用JNDI数据源

servlet容器一般都允许通过JNDI配置数据源，利用spring我们可以像使用Spring bean一样的配置jNDI中数据源的引用，
并将其装配到需要的类中。  
- xml配置形式 见applicationContext.xml 
- 代码见com.learn.spiactn.DatasourceConfig

### 10.2.2 使用数据源连接池

spring没有提供数据源连接池的实现，但是有多种开源的选择：
- Apache Commons DBCP
- c3p0
- BoneCP
这些连接池的大多数都能配置为Spring的数据源，在一定程度上与Spring自带的DriverManagerDataSource或
SingleConnectionDataSource很类似。
例如，下面就是配置DBCP BasicDataSource的方式：

- XML方式
    ```xml
    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
        p:driverClassName="org.h2.Driver"
        p:url="jdbc:h2:tcp://localhost/~/spitter"
        p:username="aa" 
        p:password="" 
        p:initialSize="5" 
        p:maxActive="10"
    />
    ```
- Java方式

    ```
    @Bean
    public BasicDataSource poolDataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:tcp://localhost/~/spitter");
        ds.setUsername("aa");
        ds.setPassword("");
        ds.setInitialSize(5);
        ds.setMaxActive(10);
        return ds;
    }
    ```
### 10.2.3 基于JDBC驱动的数据源

在Spring中，通过JDBC驱动定义数据源是最简单的配置方式。Spring提供了三个这样的数据源类供选择：
- DriverManagerDataSource：在每个连接请求时都会返回一个新的连接，与DBCP的BasicDataSource不同，由DriverManagerDataSource提供的连接并没有
进行池化管理
- SimpleDriverDataSource： 与DriverManagerDataSource工作方式类似，但是他是直接使用HDBC驱动，来解决特定环境下的类加载问题，这样的环境包括OSGI容器
- SingleConnectionDataSource：在每个连接请求时都会返回同一个的连接，尽管他不是严格意义上的连接池数据源，但是你可以将其视为只有一个连接的池。

配置方式和配置参数都与DBCP类似

### 10.2.4 使用嵌入式的数据源
嵌入式的数据库在生产环境中并没有太大的用处，但是对于开发和测试来讲，嵌入式数据库都是很好的可选方案，
Spring的jdbc命名空间能够简化嵌入式数据库的配置。  
例如，如下的程序清单展现了如何使用jdbc命名空间 来配置嵌入式的H2数据库，他会预先加载一组测试数据。
```xml
<jdbc:embedded-database id="dataSource" type="H2">
<jdbc:script location="com/habuma/spitter/db/jdbc/schema.sql"></jdbc:script>
<jdbc:script location="com/habuma/spitter/db/jdbc/test-data.sql"></jdbc:script>
</jdbc:embedded-database>
```
java版本的配置如下

```shell

    @Bean
    public DataSource embeddedDatasource() {

        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .addScript("classpath:test-data.sql")
                .build();

    }
```
### 10.2.5 使用profile选择数据源
在@Bean注解的方法上增加@Profile注解即可

## 10.3 在Spring中使用JDBC
spring支持直接使用jdbc，使用jdbc可以在更底层的地方处理数据，可以针对sql进行调优等等，灵活性较高

### 10.3.1 面对失控的JDBC代码
使用jdbc时，大部分的代码将不涉及到我们的业务逻辑，会被大量的打开链接，
关闭连接，异常处理占用，同时这些处理也绝大部分是重复的，
所以如何将这部分代码抽离出来呢？Spring给我们提供了一种方式，JDBC模板

### 10.3.2 使用JDBC模板

spring的JDBC框架承担了资源管理和异常处理的工作，从而简化了jdbc代码，现在我们只需编写自己的核心业务代码。
spring为JDBC提供了三个模板类供选择：
- JdbcTemplate ：最基本的Spring JDBC模板，这个模板支持简单的JDBC数据库访问功能以及基于索引的查询语句。
- NamedParameterJdbcTemplate :使用该模板类执行查询时，可以将值以命名参数的形式绑定到sql中，而不是简单的索引参数
- SimpleJdbcTemoplate： 改模板利用Java5的一些特性 如自动装箱，反省以及可变参数列表来简化jdbc模板的使用

目前SimpleJdbcTemplate已经被废弃，其特性被转移到了JdbcTemplate中，而只有你需要使用命名参数的时候，才会使用NameedParameterJdbcTemplate，所以对大部分功能都是在使用JDBCTemplate

#### 使用JdbcTemplate来插入数据

为了让JdbcTemplate正常工作，只需要为其设置DataSource即可
具体代码见SpitterRepository

#### 在JdbcTemplate中使用java8的lambda表达式
见SpitterRepository

#### 使用命名参数
具体细节也就是使用一个map传参，没啥意思
