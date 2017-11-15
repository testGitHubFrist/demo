package com.nilo.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.nilo.config.RedisTemplateImp;
import com.nilo.dao.VideoTagMapper;
import com.nilo.model.sysTables;
import com.nilo.security.Principal;

/**
 * 
 * @author 张善闯
 * <p>这是个测试类
 */
@Controller
public class TestAction extends BaseAction {
	
	private static final Logger logger = LoggerFactory.getLogger(TestAction.class);
	@Autowired VideoTagMapper videoTagMapper;
	@Autowired RedisTemplateImp redisTemplateImp;
	@Value("${database}")
	private String database;
	
	@RequestMapping(value="test")
	public String test(Model model) throws Exception{
		logger.info("----------------begin------------------");
		Subject currentUser = SecurityUtils.getSubject();
		Principal principal=(Principal) currentUser.getPrincipal();
		principal.getLoginAccount();
		String name=redisTemplateImp.get("a");
		if(name==null){
			redisTemplateImp.set("a", "1");
			redisTemplateImp.expire("a", 10);
			
		}
		model.addAttribute("a", "adwdsa");
		List<String> list=new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		model.addAttribute("list",list);
		redisTemplateImp.delete("a");
		return "test-jsp";
	}
	
	@RequestMapping(value="getDataBase")
	public String getDataBase(Model model) throws Exception{
		List<sysTables> list=videoTagMapper.queryDataBaseTablesByName(database);
		System.out.println(JSON.toJSON(list));
		model.addAttribute("tables", list);
		return "sysTables";
	}
}
