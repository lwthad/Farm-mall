package com.farm.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.farm.common.pojo.EUDataGridResult;
import com.farm.common.pojo.farmResult;
import com.farm.common.utils.IDUtils;
import com.farm.mapper.TbItemDescMapper;
import com.farm.mapper.TbItemMapper;
import com.farm.mapper.TbItemParamItemMapper;
import com.farm.mapper.TbItemParamMapper;
import com.farm.pojo.TbContentExample;
import com.farm.pojo.TbItem;
import com.farm.pojo.TbItemDesc;
import com.farm.pojo.TbItemExample;
import com.farm.pojo.TbItemExample.Criteria;
import com.farm.pojo.TbItemParamItem;
import com.farm.service.ItemService;
/**
 * 
 * @author tao 
 * @E-mail ChinaPRNo1@163.com
 * @version 2018年10月27日下午7:55:04
 */
@Service
public class ItemServiceImp implements ItemService {
	@Autowired
	TbItemMapper itemMapper;
	
	@Autowired
	TbItemDescMapper itemDescMapper;
	
	@Autowired
	TbItemParamItemMapper ItemParamItemMapper;
	/**
	 * 根据商品id查询商品信息
	 */
	@Override
	public TbItem getItemById(Long itemId) {
		//TbItem item = itemMapper.selectByPrimaryKey(itemId);
		
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		
		List<TbItem> items = itemMapper.selectByExample(example);
		if(items != null && items.size() > 0){
			TbItem tbItem = items.get(0);
			return tbItem;
		}
		return null;
	}
	/**
	 * 查询商品列表,条件查询
	 */
	@Override
	public EUDataGridResult getItemlist(int page, int rows) {
		//建立查询条件对象
		TbItemExample example = new TbItemExample();
		//分页处理
		PageHelper.startPage(page, rows);
		//执行条件查询
		List<TbItem> list = itemMapper.selectByExample(example);
		
		//创建一个分页返回值对象
		EUDataGridResult result = new EUDataGridResult();
		//将查询记录装入返回结果中
		result.setRows(list);
		//将查询记录封装到pageInfo中
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//取出记录总数
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	/**
	 * 添加商品
	 * @throws Exception 
	 */
	@Override
	public farmResult addItem(TbItem tbItem, String desc, String itemParam) throws Exception {
		//把tbItem补全
		//生成商品id
		long id = IDUtils.genItemId();
		tbItem.setId(id);
		//生成商品状态 1-正常 2-下架 3-删除
		tbItem.setStatus((byte) 1);
		tbItem.setCreated(new Date());
		tbItem.setUpdated(new Date());
		
		//插入商品描述（在同一方法中，保证是同一事物）
		farmResult insertDesc = InsertDesc(id, desc);
		if(insertDesc.getStatus() !=200){
			throw new Exception();
		}
		//插入规格参数模板（在同一方法中，保证是同一事物）、
		farmResult insertItemParamItem = insertItemParamItem(id, itemParam);

		//插入到数据库中
		itemMapper.insert(tbItem);
		return farmResult.ok();
	}
	
	private farmResult InsertDesc(Long itemId, String desc) {
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(itemId);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(new Date());
		tbItemDesc.setUpdated(new Date());
		int insert = itemDescMapper.insert(tbItemDesc);
		
		return farmResult.ok();
	}
	
	private farmResult insertItemParamItem(Long itemId, String itemParam){
		TbItemParamItem itemParamItem = new TbItemParamItem();
		
		itemParamItem.setItemId(itemId);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		itemParamItem.setParamData(itemParam);
		int insert = ItemParamItemMapper.insert(itemParamItem);
		return farmResult.ok();
	}
}
