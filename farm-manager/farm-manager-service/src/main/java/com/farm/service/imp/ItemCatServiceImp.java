package com.farm.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farm.common.pojo.EUTreeNode;
import com.farm.mapper.TbItemCatMapper;
import com.farm.pojo.TbItemCat;
import com.farm.pojo.TbItemCatExample;
import com.farm.pojo.TbItemCatExample.Criteria;
import com.farm.service.ItemCatService;

/**
 * <p>商品分类管理</p>
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2018年10月27日下午11:13:54
 * @version  1.0
 */
@Service  //缺少该注解将导致对应Controller注入错误
public class ItemCatServiceImp implements ItemCatService {

	@Autowired
	TbItemCatMapper itemCatMapper;
	
	@Override
	public List<EUTreeNode> getCatList(Long parentId) {
		//创建查询条件
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//根据条件查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		
		ArrayList<EUTreeNode> resultlist = new ArrayList<>();

		for (TbItemCat tbItemCat : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			resultlist.add(node);
		}
		
		return resultlist;
	}

}
