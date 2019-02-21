package com.farm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.farm.common.pojo.EUDataGridResult;
import com.farm.common.pojo.farmResult;
import com.farm.pojo.TbItemParam;
import com.farm.service.ItemParamService;

/**
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2018年11月16日下午10:26:10
 * @version  1.0
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	
	@Autowired
	private ItemParamService itemParamService; 
	
	/**
	 * 分页查询商品类目
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult queryItemParam(Integer page,Integer rows){
		EUDataGridResult result = itemParamService.queryItemParam1(page, rows);
		return result;
	}
	
	
	/**
	 * 查询选中的商品类目是否已存在
	 * @param itemCatId
	 * @return
	 */
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public farmResult getItemParamByCid(@PathVariable Long itemCatId){
		farmResult result = itemParamService.getItemParamByCid(itemCatId);
		
		return result;
	}
	
	/**
	 * 新增商品规格参数
	 * @param cid
	 * @param paramData
	 * @return
	 */
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public farmResult insertItemParam(@PathVariable Long cid, String paramData){
		TbItemParam tbItemParam = new TbItemParam();
		tbItemParam.setItemCatId(cid);
		tbItemParam.setParamData(paramData);
		farmResult result = itemParamService.insertItemParam(tbItemParam);
		return result;
	}
	
	/**
	 * 删除商品类目对应的规格参数
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public farmResult insertItemParam(Long ids){
		itemParamService.deleteItemParam(ids);
		return farmResult.ok();
	}
}
