# 16. 使用Spring MVC创建Rest API

# 16.1了解REST
webservice关心行为和处理，rest关心数据和资源

## 16.1.1 REST的基础知识
当讨论REST时，一种常见的错误就是把REST视为基于URL开发的web服务，将rest视为另一种RPC调用
机制，就像soap一样，不过是使用Http来实现。  
实际上，rest与RPC的理念是完全不同的，RPC是面向服务的，并关注与行为和动作，而rest是面向资源的，强调应用程序的
事务和名词
在REST中，资源通过URL来定位，通过httpmethod来确定执行的动作。

## 16.1.2 Spring 是如何支持REST的 
Spring很早就有到处REST资源的需求，从3.0版本开始，Spring针对Spring MVC的一些增强功能对REST提供了良好的
支持。当前的4.0版本中，Spring支持一下方式来创建REST资源：
- 控制器可以处理所有的HTTP方法，包含主要的GET，POST，PUT，DELETE，Spring3.2以上的版本还支持PATCH方法
- 借助@PathVariable注解，控制器能够处理参数化的URL
- 借助Spring的视图和视图解析器，资源能够以多种方式进行表述，包括将模型数据渲染为XML，JSON，Atom以及RSS的View实现

- 可以使用ContentNegotiatingViewResolver来选择最适合客户端的表述
- 借助@ResponseBody注解和各种HTTPMethodConverter实现，能够替换基于视图的渲染方式
- @RequestBody注解和HTTPMethodConverter实现可以将传入的Http数据转化为传入控制器方法的JAVa对象
- 借助RestTemplate ，Spring可以方便的使用REST资源

# 16.2 创建第一个REST端点

我们要创建一个REST服务，表述是一个重要方面，它是关于客户端和服务端是如何通信的。
Spring提供了两种方法将资源的Java表属性是转换为发送给客户端的表述形式。
- 内容协商：选择一个视图，它能够将模型渲染为城乡给客户端的表述形式
- 消息转换器：通过一个消息转换器将控制器所返回的对象转换为客户端的表述形式
我们于第5章和第6章已经讨论过视图解析器，所以首先看下如何使用内容协商来选择视图或者视图解析器

## 16.1 协商资源表述
Spring的ContentNegotiatingViewResolver是一个特殊的视图解析器，他考虑到了客户端所需要的
内容类型，按照最简单的方式，ContentNegotiatingViewResolver可以按照如下形式进行配置：

```java
    @Bean
    public ViewResolver viewResolver(){
        return new ContentNegotiatingViewResolver();
    }
```
它主要做了两件事情：
- 确定请求的媒体类型
- 找到适合请求媒体类型的最佳视图

### 确定请求的媒体类型
ContentNegotiatingViewResolver确定媒体类型需要如下几个步骤：
1. 文件扩展名
2. Accept头信息
3. 如果1，2都无法获取到合适的信息，那么将会使用“/”作为默认的内容类型，意味着客户端
需要接受服务端发送的任何类型的表述。  
一旦内容类型确定后，ContentNegotiatingViewResolver就将该逻辑视图解析为渲染模型的view，
但是他并不直接渲染视图，而是交给其他视图解析器，让他们来解析视图。

### 影响媒体类型的选择

在上述的选择过程中，我们阐述了确定所请求媒体的默认策略，但是通过为其设置一个ContentNegotiationManager，
我们可以改变它的行为。借助Content-NegotiationManager我们能做到的事情如下所示：
- 指定默认的内容类型，如果根据请求无法得到内容类型的话，将会使用默认值
- 通过请求参数指定内容类型
- 忽视请求的Accept头部信息
- 将请求的扩展名映射为特定的媒体类型
- 将jaf作为根据扩展名查找媒体类型的方案

有三种方案配置它：
1. 直接声明一个ContentNegotiationManager的bean
2. 通过ContentNegotiationManagerFactoryBean简介创建Bean
3. 重载 WebMvcConfigureerAdaptor的configureContentNegotiation()方法

直接创建ContentNegotiationManager比较复杂，一般都是使用后两种方式。

具体ContentNegotiationManager能做的事，以及配合viewResolver和View来做的事
详见代码

### ContentNegotiatingViewResolver的优势与限制
ContentNegotiatingViewResolver的最大优势在于他在SpringMvc上构建了REST资源表示层，
控制器代码无需修改，相同的一套控制器方法能够为面向人类的用户产生html内容，也能针对不是人类的
的客户端产生JSON或XML。
但是他有如下几个限制：
1. 如果JSON解析的接口和XML解析的接口重合度不高的情况，ContentNegotiatingViewResolver的优势就无法发挥出来
2. 作为ViewReolver的实现，ContentNegotiatingViewResolver只能决定资源如何选然道客户端，却无法规定客户端要发送什么数据
到控制器
3. ContentNegotiatingViewResolver渲染的数据格式与实际想要的格式可能不符

基于这些考虑，通常我们不再使用ContentNegotiatingViewResolver，而是使用Spring消息转换功能
来生成资源表述。

### 16.2.2 使用HTTP信息转换器

消息转换提供了一种更为直接的方式，它能够将控制器产生的数据转换为服务于客户端的表述形式。
当使用消息转换功能时，DispatcherServlet不再需要那么麻烦的将模型数据传送到视图中，
实际上，这里根本就没有模型，也没有视图，只有控制器产生的数据，以及消息转换器转换数据之后
产生的资源表述。
Spring自带了多种转换器，除了其中的五个以外都是自注册的，不需要配置，但是需要添加一些库道程序的类路径下。

而且为了支持消息转换，我们需要对SpringMvc的编程模型进行一些小调整

### 在响应体重返回资源状态

在正常情况下，当处理方法返回Java对象时，这个对象会放在模型中并放在视图中渲染使用。
但是如果使用了消息转换功能的话，我们需要告诉Spring跳过正常的模型/视图流程，并使用消息转换器。
有不小方式都能做到，但是最简单的方式就是添加@ResponseBody注解

### 在请求体中接收资源状态

@ResponseBody

### 为控制器默认设置消息转换

@RestController

## 16.3 提供资源之外的其他内容

一个良好的REST API 不仅能够传递资源，还能够给客户端提供额外的元数据，
帮助客户端理解资源或者在请求中出现了什么情况

### 16.3.1 发送错误信息到客户端

Spring提供了几种方式来返回状态情况

- 使用@ResponseStatus注解可以指定状态码
- 控制其方法可以返回ResponseEntity对象，该对象能够包含更多响应相关的元数据
- 异常处理器能够应对错误场景，这样处理器方法就能关注于正常的状况

#### 使用ResponseEntity

ResponseEntity可以返回一个包裹对象，包含一些http状态码

#### 处理错误

就是使用@ExceptionHandler注解来统一处理错误

### 16.3.2 在响应中设置头部信息
