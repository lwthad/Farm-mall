package com.farm.controller;
/**
 * @author tao 
 * @E-mail ChinaPRNo1@163.com
 * @version 2018年10月27日下午8:23:14
 */

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.farm.mapper.TbItemMapper;
import com.farm.pojo.TbItem;
import com.farm.pojo.TbItemExample;

public class TestPageHelper {
	
	@Test
	public void TestPage(){
		//创建一个Spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		//从spring容器中获得mapper对象
		TbItemMapper itemmapper = applicationContext.getBean(TbItemMapper.class);
		TbItemExample example = new TbItemExample();
		//执行查询，并分页
		PageHelper.startPage(3, 5);
		List<TbItem> list = itemmapper.selectByExample(example);
		
		for (TbItem tbItem : list) {
			System.err.println(tbItem.getTitle());
		}
		//对list进行包装
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		System.out.println("共有商品： " + total + " 条.");
	}
	
	
	
}
