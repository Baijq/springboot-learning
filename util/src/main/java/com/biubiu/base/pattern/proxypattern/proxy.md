# 代理模式  

代理模式可以在不修改被代理对象的基础上，通过扩展代理类，进行一些功能的附加与增强。值得注意的是，代理类和被代理类应该共同实现一个接口，或者是共同继承某个类。

## 1. 静态代理  

>静态代理的实现模式一般是： 
 
首先创建一个接口（JDK代理都是面向接口的），然后创建具体实现类来实现这个接口，然后再创建一个代理类同样实现这个接口，不同之处在于，具体实现类的方法中需要将接口中定义的方法的业务逻辑功能实现，而代理类中的方法只要调用具体类中的对应方法即可，这样我们在需要使用接口中的某个方法的功能时直接调用代理类的方法即可，将具体的实现类隐藏在底层。

## 2. 动态代理

1. 动态代码涉及了一个非常重要的类 Proxy。正是通过 Proxy 的静态方法 newProxyInstance 才会动态创建代理。

```java
public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)
//loader 自然是类加载器
//interfaces 代码要用来代理的接口
//h 一个 InvocationHandler 对象
```

2. 初学者应该对于 InvocationHandler 很陌生，下面说一下

```java
public interface InvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable;
}
//proxy 代理对象
//method 代理对象调用的方法
//args 调用的方法中的参数
```

[参考博客](https://blog.csdn.net/briblue/article/details/73928350)