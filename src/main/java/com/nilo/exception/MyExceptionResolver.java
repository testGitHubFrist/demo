package com.nilo.exception;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
 * @author 张善闯
 * <p>spring MVC 异常处理
 */
public class MyExceptionResolver implements HandlerExceptionResolver{

	private static final Logger logger = LoggerFactory.getLogger(MyExceptionResolver.class);

	@Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        Map<String, Object> model = new ConcurrentHashMap<String, Object>();
        model.put("ex", ex);
        // 可以细化异常信息，给出相应的提示
        logger.info("==========发生了异常：");
        logger.info("==========异常类型："+ex.getClass().getSimpleName());
        logger.info("==========异常描述："+ex.getMessage());
        logger.info("==========异常原因："+ex.getCause());
        if("UnknownAccountException".equals(ex.getClass().getSimpleName())){
        	 model.put("error", ex.getMessage());
        	 return new ModelAndView("/login",model);
        }else{
        	 return new ModelAndView("error/error",model);
        }
    }

}
