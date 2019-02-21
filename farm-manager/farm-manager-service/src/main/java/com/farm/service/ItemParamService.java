package com.farm.service;
/**
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2018年11月16日下午10:18:44
 * @version  1.0
 */

import java.util.List;

import com.farm.common.pojo.EUDataGridResult;
import com.farm.common.pojo.farmResult;
import com.farm.pojo.TbItemParam;

public interface ItemParamService {
	
	farmResult getItemParamByCid(Long cid);

	farmResult insertItemParam(TbItemParam itemParam);

	List<TbItemParam> queryItemParam(Integer page, Integer rows);
	
	EUDataGridResult queryItemParam1(Integer page, Integer rows);

	void deleteItemParam(Long ids);
}
