package cn.genlei.ydms.annotation;

import java.lang.annotation.*;

/**
 * @author: Junfeng
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface YdmsLog {

    String value() default "";

    String action() default "";
}