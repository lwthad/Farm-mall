package com.farm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.farm.common.pojo.EUDataGridResult;
import com.farm.common.pojo.farmResult;
import com.farm.pojo.TbContent;
import com.farm.service.ContentService;

/**
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2018年11月18日下午8:39:41
 * @version  1.0
 */
@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	/**
	 * 查询内容，不分页
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/query/list")
	@ResponseBody
	public List<TbContent> queryContentList(Long categoryId,Integer page,Integer rows){
		
		List<TbContent> contentList = contentService.contentList1(categoryId,page,rows);
		return contentList;
	}
	/**
	 * 查询内容，分页
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	/*@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult queryContentList(Long categoryId,Integer page,Integer rows){
		
		EUDataGridResult contentList = contentService.contentList(categoryId,page,rows);
		return contentList;
	}*/
	
	/**
	 * 新增内容
	 * @param content
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public farmResult insertContent(TbContent content){
		farmResult result = contentService.insertContent(content);
		return result;
	}
	
	//$.post("/rest/content/edit",$("#contentEditForm").serialize(), function(data){
//	if(data.status == 200){
	@RequestMapping("/edit")
	@ResponseBody
	public farmResult updateContent(TbContent content){
		farmResult result = contentService.updateContent(content);
		return result;
	}
	
}
