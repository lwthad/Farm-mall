package com.farm.service;

import com.farm.common.pojo.EUDataGridResult;
import com.farm.common.pojo.farmResult;
import com.farm.pojo.TbItem;

public interface ItemService {
	TbItem getItemById(Long itemId);
	
	EUDataGridResult getItemlist(int page, int rows); 
	
	farmResult addItem(TbItem tbItem, String desc, String itemParam) throws Exception;
}
