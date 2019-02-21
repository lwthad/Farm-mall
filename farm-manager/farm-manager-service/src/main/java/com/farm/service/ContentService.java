package com.farm.service;

import java.util.List;

import com.farm.common.pojo.EUDataGridResult;
import com.farm.common.pojo.farmResult;
import com.farm.pojo.TbContent;

/**
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2018年11月18日下午8:43:19
 * @version  1.0
 */
public interface ContentService {
	List<TbContent> contentList1(Long categoryId, Integer page, Integer rows);
	
	EUDataGridResult contentList(Long categoryId, Integer page, Integer rows);
	
	farmResult insertContent(TbContent content);

	farmResult updateContent(TbContent content);
	
}
