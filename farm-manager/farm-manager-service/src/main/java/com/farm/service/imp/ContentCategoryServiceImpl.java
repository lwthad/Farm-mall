package com.farm.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farm.common.pojo.EUTreeNode;
import com.farm.common.pojo.farmResult;
import com.farm.mapper.TbContentCategoryMapper;
import com.farm.pojo.TbContentCategory;
import com.farm.pojo.TbContentCategoryExample;
import com.farm.pojo.TbContentCategoryExample.Criteria;
import com.farm.service.ContentCategoryService;

/**
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2018年11月18日下午4:36:36
 * @version  1.0
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper; 
	
	/**
	 * 查看内容分类节点
	 */
	@Override
	public List<EUTreeNode> getCategoryList(Long parentId) {
		//根据parentId查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<>();
		for(TbContentCategory tbContentCategory : list){
			//创建一个节点
			EUTreeNode euTreeNode = new EUTreeNode();
			euTreeNode.setId(tbContentCategory.getId());
			euTreeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
			euTreeNode.setText(tbContentCategory.getName());
			
			resultList.add(euTreeNode);
		}
		return resultList;
	}
	
	/**
	 * 新增内容分类节点
	 */
	@Override
	public farmResult createContentCategory(Long parentId, String name) {
		
		//创建一个pojo
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setName(name);
		int result = contentCategoryMapper.insert(contentCategory);
		
		//查看父节点的isParent字段是否为TRUE，如果不是改为TRUE
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentCat.getIsParent()){
			TbContentCategory record = new TbContentCategory();
			record.setId(parentCat.getId());
			record.setIsParent(true);
			int updateResult = contentCategoryMapper.updateByPrimaryKeySelective(record);
			//parentCat.setIsParent(true);
		}
		return farmResult.ok(contentCategory);
	}

	/**
	 * 删除节点，存在父节点为空的情况所以进行判断
	 */
	@Override
	public void deleteContentCategory(Long parentId, Long id) {
		//执行删除选中的节点
		int deleteResult = contentCategoryMapper.deleteByPrimaryKey(id);
		//查询父节点是否还存在子节点，若不存在，则将isParent改为false
		if(parentId != null){
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(parentId);
			List<TbContentCategory> result = contentCategoryMapper.selectByExample(example);
			//判断子节点是否存在，若不存在则将isParent改为false
			if(result.isEmpty()){
				TbContentCategory record = new TbContentCategory();
				record.setId(parentId);
				record.setIsParent(false);
				//更新父节点的isParent字段为false
				contentCategoryMapper.updateByPrimaryKey(record);
			}
		}
		//还需要判断删除de节点是否存在子节点，若存在，则全部删除
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		List<TbContentCategory> result2 = contentCategoryMapper.selectByExample(example);
		if(!result2.isEmpty()){
			for(TbContentCategory tbContentCategory : result2){
				Long id2 = tbContentCategory.getId();
				int result = contentCategoryMapper.deleteByPrimaryKey(id2);
			}
		}
	}
	
	/**
	 * 修改节点名称
	 */
	@Override
	public void updatecontentCategory(Long id, String name){
		TbContentCategory record = new TbContentCategory();
		record.setId(id);
		record.setName(name);
		int updateResult = contentCategoryMapper.updateByPrimaryKeySelective(record);
	}
	
}
