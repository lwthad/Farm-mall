package com.farm.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.farm.common.pojo.EUDataGridResult;
import com.farm.common.pojo.farmResult;
import com.farm.mapper.TbItemParamMapper;
import com.farm.pojo.TbItem;
import com.farm.pojo.TbItemParam;
import com.farm.pojo.TbItemParamExample;
import com.farm.pojo.TbItemParamExample.Criteria;
import com.farm.service.ItemParamService;

/**
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2018年11月16日下午10:21:35
 * @version  1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	/**
	 * 查询规格参数模版
	 */
	@Override
	public farmResult getItemParamByCid(Long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		if(list != null && list.size() > 0){
			return farmResult.ok(list.get(0));
		}
		return farmResult.ok();
	}

	@Override
	public farmResult insertItemParam(TbItemParam itemParam) {
		//补全pojo
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入到规格参数模板表
		itemParamMapper.insert(itemParam);
		return farmResult.ok();
	}
	
	/**
	 * 查询商品规格参数
	 */
	@Override
	public List<TbItemParam> queryItemParam(Integer page, Integer rows) {
		TbItemParamExample example = new TbItemParamExample();
		//分页处理
//		PageHelper.startPage(page, rows);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		//创建一个分页返回值对象
//		EUDataGridResult result = new EUDataGridResult();
//		//将查询记录装入返回结果中
//		result.setRows(list);
//		//将查询记录封装到pageInfo中
//		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
//		//取出记录总数
//		result.setTotal(pageInfo.getTotal());
//		return result;
		return list;
	}

	@Override
	public EUDataGridResult queryItemParam1(Integer page, Integer rows) {
		TbItemParamExample example = new TbItemParamExample();
		//分页处理
//		rows = 5;
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		//创建一个分页返回值对象
		EUDataGridResult result = new EUDataGridResult();
		//将查询记录装入返回结果中
		result.setRows(list);
		//将查询记录封装到pageInfo中
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		//取出记录总数
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public void deleteItemParam(Long ids) {
		itemParamMapper.deleteByPrimaryKey(ids);
	}

}
