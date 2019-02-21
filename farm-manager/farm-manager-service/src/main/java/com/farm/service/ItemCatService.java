package com.farm.service;
/**
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2018年10月27日下午11:11:35
 * @version  1.0
 */

import java.util.List;

import com.farm.common.pojo.EUTreeNode;

public interface ItemCatService {
	
	List<EUTreeNode> getCatList(Long parentId);
}
