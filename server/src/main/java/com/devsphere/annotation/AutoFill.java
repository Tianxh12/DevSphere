package com.devsphere.annotation;

import com.devsphere.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用于标记需要进行公共字段填充的方法
 */

@Target({ElementType.METHOD})  // 用于指定自定义注解可以应用到哪些程序元素上。 在本例中，它指定了自定义注解可以应用到方法上。
@Retention(RetentionPolicy.RUNTIME) // 用于指定自定义注解的保留策略。 在本例中，它指定了自定义注解在运行时保留，以便在运行时可以通过反射机制访问到注解的信息。
public @interface AutoFill {

    /**
     * 数据库操作类型：INSERT 为插入操作；UPDATE 为更新操作
     */
    OperationType value();

}
