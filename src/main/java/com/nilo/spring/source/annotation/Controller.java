package com.nilo.spring.source.annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Controller注解
 * @author 超越
 * @Date 2016年11月29日,上午10:37:30
 * @motto 人在一起叫聚会，心在一起叫团队
 * @Version 1.0
 * 
 * 深入理解Java：注解（Annotation）自定义注解入门 https://www.cnblogs.com/peida/archive/2013/04/24/3036689.html
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {
	String value() default "";
}
