
 初始项目

每个包的作用：

| **名称** | **说明**                         |
|--------|--------------------------------|
| Entity | 实体，通常和数据库中的表对应                 |
| DTO    | 数据传输对象，通常用于程序中各层之间传递数据         |
| VO     | 视图对象，为前端展示数据提供的对象              |
| POJO   | 普通Java对象，只有属性和对应的getter和setter |


**nginx 反向代理的好处：**

- 提高访问速度

  因为nginx本身可以进行缓存，如果访问的同一接口，并且做了数据缓存，nginx就直接可把数据返回，不需要真正地访问服务端，从而提高访问速度。

- 进行负载均衡

  所谓负载均衡,就是把大量的请求按照我们指定的方式均衡的分配给集群中的每台服务器。

- 保证后端服务安全

  因为一般后台服务地址不会暴露，所以使用浏览器不能直接访问，可以把nginx作为请求访问的入口，请求到达nginx后转发到具体的服务中，从而保证后端服务的安全。

### 2024-11-04

- 新增员工save方法

- 添加拦截器
  修改`WebMvcConfiguration` extends `WebMvcConfigurationSupport` 添加注解`@Configuration`

- 引入`ThreadLocal` 线程安全，存放局部变量
  
  ThreadLocal为每个线程提供单独一份存储空间，具有线程隔离的效果，只有在线程内才能获取到对应的值，线程外则不能访问。
  
  public void set(T value) 	设置当前线程的线程局部变量的值 
  
  public T get() 		返回当前线程所对应的线程局部变量的值

  public void remove()        移除当前线程的线程局部变量

- 定义分页查询类，引入`PageHelper`分页查询

- 全局统一对日期类型进行格式处理

### 2024-11-06

已完成

- 新增"启用"和"禁用"员工账号功能

- 根据id查询员工
- 修改个人信息



知识点

- @NoArgsConstructor ： 生成一个无参数的构造方法
- @AllArgsContructor： 会生成一个包含所有变量的构造方法，默认生成的方法是 public 的
- @PutMapping : 通常用于更新资源
- Mybatis中写`<if test="name != null">name = #{name},</if>` 一定要注意`,`





### 2024-11-07

已完成

- 公共字段自动填充






知识点

- 使用AOP切面实现公共字段自动填充（创建/修改时间，创建/修改id）   ---   枚举、注解、AOP、反射

  1). 自定义注解 AutoFill，用于标识需要进行公共字段自动填充的方法

  2). 自定义切面类 AutoFillAspect，统一拦截加入了 AutoFill 注解的方法，通过反射为公共字段赋值

  3). 在 Mapper 的方法上加入 AutoFill 注解

- 定义注解时常用

```java
@Target({ElementType.METHOD})  // 用于指定自定义注解可以应用到哪些程序元素上。 在本例中，它指定了自定义注解可以应用到方法上。
@Retention(RetentionPolicy.RUNTIME)  // 用于指定自定义注解的保留策略。 在本例中，它指定了自定义注解在运行时保留，以便在运行时可以通过反射机制访问到注解的信息。
```



#### 2024-11-14

已完成

- 上传图片至Gitee

- 新增模块



知识点

- `@Transactional` 注解是Spring框架中用于管理事务的注解，主要用于声明性事务管理。它能帮助我们简化数据库事务的处理，使得事务的提交、回滚等操作在方法执行时自动完成。
- xml中循环插入数据

```xml
<mapper namespace="com.devsphere.mapper.DishFlavorMapper">
    <insert id="insertBatch">
        insert into dish_flavor (dish_id, name, value) VALUES
        <foreach collection="flavors" item="df" separator=",">
            (#{df.dishId},#{df.name},#{df.value})
        </foreach>
    </insert>
</mapper>
```

