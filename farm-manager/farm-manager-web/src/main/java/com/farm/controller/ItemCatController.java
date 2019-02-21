package com.farm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.farm.common.pojo.EUTreeNode;
import com.farm.service.ItemCatService;

/**
 * <p>商品分类管理</p>
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2018年10月28日上午12:05:54
 * @version  1.0
 */
@Controller
@RequestMapping("/item")
public class ItemCatController {
	@Autowired
	ItemCatService itemCatService;
	
	@RequestMapping("/cat/list")
	@ResponseBody
	public List<EUTreeNode> getCatList(@RequestParam(value="id",defaultValue="0")Long parentId){
		List<EUTreeNode> catList = itemCatService.getCatList(parentId);
		
		return catList;
	}
}
