package com.devsphere.aspect;

import com.devsphere.annotation.AutoFill;
import com.devsphere.constant.AutoFillConstant;
import com.devsphere.context.BaseContext;
import com.devsphere.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    /**
     * 切入点
     */
    @Pointcut("execution(* com.devsphere.mapper.*.*(..)) && @annotation(com.devsphere.annotation.AutoFill)") // 表示匹配所有的方法，并且方法上有 @AutoFill 注解。
    public void autoFillPointCut() {
    }

    /**
     * 前置通知 在通知中进行公共字段的赋值
     */
    @Before("autoFillPointCut()") // 表示在切入点方法执行之前执行
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始进行公共字段自动填充...");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature(); // 获取方法签名
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class); // 获取方法上的 @AutoFill 注解
        OperationType operationType = autoFill.value();//获得数据库操作类型

        // 获取当前被拦截的方法的参数 -- 实体对象
        Object[] args = joinPoint.getArgs();

        if (args == null || args.length == 0) {
            return;
        }
        Object entity = args[0];

        // 根据不同的操作类型，为对应的属性通过反射来赋值

        // 为实体对象的公共字段赋值
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        // 根据当前的操作类型，为对应的属性通过反射来赋值
        if(operationType == OperationType.INSERT) {
            // 为4个公共字段赋值
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                //通过反射为对象属性赋值
                setCreateTime.invoke(entity,now);
                setCreateUser.invoke(entity,currentId);
                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(operationType == OperationType.UPDATE) {
            //为2个公共字段赋值
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                //通过反射为对象属性赋值
                setUpdateTime.invoke(entity,now);
                setUpdateUser.invoke(entity,currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
