package com.farm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author tao 
 * @E-mail ChinaPRNo1@163.com
 * @version 2018年10月27日下午7:56:49
 */
@Controller
public class PageController {
	
	/**
	 * 页面跳转 打开首页
	 * @return
	 */
	@RequestMapping("/")
	public String showIndex(){
		return "index";
	}
	
	/**
	 * 显示各页面
	 * @param page
	 * @return
	 */
	@RequestMapping("/{page}")
	public String showpage(String page){
		return page;
	}
	
	
}
