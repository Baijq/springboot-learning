## SpringBoot开发API接口 

[参考：https://blog.csdn.net/qq_28336351/article/details/79327357](https://blog.csdn.net/qq_28336351/article/details/79327357)

API接口返回的格式一般是**application/json**，而网页返回的格式一般是**text/html**

### 1. SringBoot提供两种方式，类注解`@RestController` 和方法注解`@ResponseBody`，这也controller返回的就是text/json格式

### 2. 请求方式映射 `@RequestMapping` 这个注解适用于所有方式的请求

1. @GetMapping
2. @PostMapping
3. @DeleteMapping
4. @PutMapping

关于请求方式及使用范围，可以参考 RESTful API

### 3. 接收参数

1. `@RequestParam`

    - name = "param", //代表提交参数名
    - required = false, //这个参数是否必需，默认是true，表示没有该参数，无法调用此方法；为false，有无该参数都可以调用。
    - defaultValue 如果参数值为空，那么就使用这个设置的默认值
2. `@PathVariable`

    我们可以在请求方法后面直接跟值，省去了 ？参数名= 。这种一般配合 @DeleteMapping、@PutMapping使用。

    ```java
        @RequestMapping("/get-info/{param}")
        public String getInfo(@PathVariable("param") Object param)
    ```
3. `@RequestHeader`

    使用了获取提交数据的 Headers 的值。我是用来接收 TOKEN





### 4. 前端AJAX请求如何获取 token的值？

```js
$.ajax({
    headers : {
        Accept: "application/json; charset=utf-8",
        'token' : '9B4BF951093F1F1A40BB2DAAA30B3838'
    },
    url: URI + '/admin/blog/add',
    type: 'POST', 
    async: true,   
    data: {
        ...
    },
    timeout: 3000,   
    dataType: 'json', 
    beforeSend: function(xhr){},
    success: function(data, textStatus, jqXHR){
        console.log(data);
    },
    error: function(xhr, textStatus){
        console.log(xhr);
    },
    complete: function(){}
 })
```
没错，就是 `@RequestHeader("token")` 问题还没结束，如果我们没在Controller，那怎么办？ `String token = request.getHeader("token");`


