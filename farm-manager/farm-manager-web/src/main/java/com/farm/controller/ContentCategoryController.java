package com.farm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.farm.common.pojo.EUTreeNode;
import com.farm.common.pojo.farmResult;
import com.farm.service.ContentCategoryService;

/**
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2018年11月18日下午4:49:41
 * @version  1.0
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContentCatList(@RequestParam(value="id",defaultValue="0")Long parentId){
		
		return contentCategoryService.getCategoryList(parentId);
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public farmResult createContentCategory(Long parentId, String name){
		farmResult result = contentCategoryService.createContentCategory(parentId, name);
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public void deleteContentCategory(Long parentId, Long id){ //问题：parentId传递不过来
		
		contentCategoryService.deleteContentCategory(parentId, id);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public void updateContentCategory(Long id, String name){
		
		contentCategoryService.updatecontentCategory(id,name);
	}
}
