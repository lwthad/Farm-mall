package com.farm.service;

import java.util.List;

import com.farm.common.pojo.EUTreeNode;
import com.farm.common.pojo.farmResult;

/**
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2018年11月18日下午4:33:49
 * @version  1.0
 */
public interface ContentCategoryService {
	
	List<EUTreeNode> getCategoryList(Long parentId);
	
	farmResult createContentCategory(Long parentId, String name);

	void deleteContentCategory(Long parentId, Long id);

	void updatecontentCategory(Long id, String name);
}
