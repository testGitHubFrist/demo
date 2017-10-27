package com.nilo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nilo.config.RedisTemplateImp;
import com.nilo.dao.VideoTagMapper;
import com.nilo.security.Principal;

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
		Subject currentUser = SecurityUtils.getSubject();
		Principal principal=(Principal) currentUser.getPrincipal();
		principal.getLoginAccount();
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
	
	@RequestMapping("show")
	public String show(){
		return "asyn-echarts";
		
	}
	
	@RequestMapping("data")
	public @ResponseBody  Map<String ,Object> data(){
		Map<String ,Object> result=new HashMap<String, Object>();
		List<Object> names=new ArrayList<Object>();
		List<Object> nums=new ArrayList<Object>();
		names.add("1");
		names.add("12");
		names.add("13");
		nums.add(21);
		nums.add(22);
		nums.add(23);
		result.put("names", names);
		result.put("nums", nums);
		return result;
	}
}
