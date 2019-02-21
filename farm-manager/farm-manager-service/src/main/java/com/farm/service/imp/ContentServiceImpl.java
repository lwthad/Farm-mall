package com.farm.service.imp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.farm.common.pojo.EUDataGridResult;
import com.farm.common.pojo.farmResult;
import com.farm.common.utils.HttpClientUtil;
import com.farm.mapper.TbContentMapper;
import com.farm.pojo.TbContent;
import com.farm.pojo.TbContentExample;
import com.farm.pojo.TbContentExample.Criteria;
import com.farm.service.ContentService;

/**
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2018年11月18日下午9:08:14
 * @version  1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;
	
	/**
	 * 添加内容
	 */
	@Override
	public farmResult insertContent(TbContent content) {
		content.setUpdated(new Date());
		content.setCreated(new Date());
		tbContentMapper.insert(content);
		
		//添加缓存同步逻辑,删除缓存内容
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return farmResult.ok();
	}
	/**
	 * 查看内容列表,不分页
	 */
	@Override
	public List<TbContent> contentList1(Long categoryId,Integer page,Integer rows) {
		//建立查询条件
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		
		List<TbContent> list = tbContentMapper.selectByExample(example);
		return list;
	}
	
	/**
	 * 查看内容列表,分页
	 */
	@Override
	public EUDataGridResult contentList(Long categoryId,Integer page,Integer rows) {
		//建立查询条件
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		//分页处理
//		rows = 3;
		PageHelper.startPage(page, rows);
		
		List<TbContent> list = tbContentMapper.selectByExample(example);
		//创建一个分页返回值对象
		EUDataGridResult result = new EUDataGridResult();
		//将查询记录装入返回结果中
		result.setRows(list);
		//将查询记录封装到pageInfo中
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		//取出记录总数
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	
	/**
	 * 编辑内容
	 */
	@Override
	public farmResult updateContent(TbContent content) {
		content.setUpdated(new Date());
		int updateResult = tbContentMapper.updateByPrimaryKeySelective(content);
		
		//添加缓存同步逻辑,删除缓存内容
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return farmResult.ok();
	}
	
	
	
	/**
	 * 删除内容
	 */
}
