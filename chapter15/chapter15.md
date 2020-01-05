# 15. Spring远程调用概览

作为一个Java开发者，我们有许多可以使用的远程调用技术，包括：

- 远程方法调用（Remote Method Invocation） RMI
- Caucho de Hessian和Burlap
- Spring 基于Http的远程服务
- 使用Jax-RPC 和JAX-WS的webservice

## 15.1 Spring 远程调用概览

远程调用时客户端和服务端之间的对话，有些功能客户端无法完成，需要向服务端请求协助
而远程应用通过远程服务暴露这些功能

|RPC模型|适用场景|
|---|---|
|RMI|不考虑网络限制时（例如防火墙），访问/发布基于Java的服务|
|Hessian或Burlap|考虑网络限制时，通过HTTP访问/发布与Javap的服务，Hessians是二进制协议，而Burlap是基于XML的|
|Http Invoker|考虑网络限制时，并希望给予XML或专有的序列化机制实现Java序列化时，访问、发布基于Spring的服务|
|JAX-RPC和JAX-WS|访问/发布平台独立的，基于SOAP的web服务|

## 15.2 使用RMI

### 15.2.1 导出RMI服务

#### 在Spring中配置RMI服务

### 15.2.2 装配RMI服务

## 15.3 使用Hessian和Burlap发布远程服务
