package com.farm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.farm.common.pojo.EUDataGridResult;
import com.farm.common.pojo.farmResult;
import com.farm.pojo.TbItem;
import com.farm.service.ItemService;

/**
 * 商品处理控制
 * @author tao 
 * @E-mail ChinaPRNo1@163.com
 * @version 2018年10月27日下午7:57:51
 */

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	/**
	 * 查询商品测试
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem queryItems(@PathVariable Long itemId){
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
	/**
	 * 分页查询商品
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemlist(Integer page,Integer rows){
		EUDataGridResult result = itemService.getItemlist(page, rows);
		
		return result;
	}
	
	/**
	 * 添加商品
	 * @param tbItem
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public farmResult addItem(TbItem tbItem, String desc, String itemParams) {
		farmResult result;
		try {
			result = itemService.addItem(tbItem, desc,itemParams);
		} catch (Exception e) {
			e.printStackTrace();
			return farmResult.build(100, "OK");
		}
		return result;
	}
}
