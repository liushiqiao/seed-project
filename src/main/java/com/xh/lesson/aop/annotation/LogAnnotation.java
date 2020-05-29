package com.xh.lesson.aop.annotation;

import java.lang.annotation.*;

/**
* @ClassName:       LogAnnotation*
* @Author:          lsq
* @CreateDate:      2020/5/26 21:32
* @UpdateUser:      lsq
* @UpdateDate:      2020/5/26 21:32
* @Version:         0.0.1
* @desc             日志注解对象，主要标注在controller上
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    /** 模块 */
    String title() default "";

    /** 功能 */
    String action() default "";
}
