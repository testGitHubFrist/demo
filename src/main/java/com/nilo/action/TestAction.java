package com.nilo.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nilo.config.RedisTemplateImp;
import com.nilo.dao.VideoTagMapper;

/**
 * 
 * @author 张善闯
 * <p>这是个测试类
 */
@Controller
public class TestAction {
	
	private static final Logger logger = LoggerFactory.getLogger(TestAction.class);
	@Autowired VideoTagMapper videoTagMapper;
	@Autowired RedisTemplateImp redisTemplateImp;
	
	@RequestMapping(value="test")
	public String test(Model model) throws Exception{
		logger.info("----------------begin------------------");
//		redisTemplateImp.set("a", "张善闯");
		String name=redisTemplateImp.get("a");
		
		if(name==null){
			redisTemplateImp.set("a", "1");
			redisTemplateImp.expire("a", 10);
			
		}
		
		model.addAttribute("a", "adwdsa");
		/*List list=videoTagMapper.queryTagListByVideoId("a");*/
		List<String> list=new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		model.addAttribute("list",list);
		logger.info("----------------end------------------");
		logger.info("changmaidnsaoifh");
		logger.info("changmaidnsaoifh1");
		logger.info("qwer");
		logger.info("----------------e1------------------");
		logger.info("----------------zsc------------------");
		logger.info("----------------e2------------------");
//		redisTemplateImp.delete("a");
		return "test-jsp";
	}
}
