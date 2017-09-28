package com.nilo.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	@RequestMapping("test")
	public String test(Model model) throws Exception{
		logger.info("----------------begin------------------");
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
		logger.info("----------------e1------------------");
		return "test-jsp";
	}
}
