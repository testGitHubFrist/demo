package com.nilo.spring.source.annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Quatifier注解
 * @author 超越
 * @Date 2016年11月29日,上午10:47:52
 * @motto 人在一起叫聚会，心在一起叫团队
 * @Version 1.0
 */
@Target({ ElementType.FIELD }) // 代表注解的注解
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Quatifier {
	String value() default "";
}
