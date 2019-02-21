package com.farm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


/**
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2018年10月29日下午3:48:17
 * @version  1.0
 */
public class timeFormat {
	
	@Test
	public void test(){
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("/yyyy/MM/dd");  
		String dateString = formatter.format(date);  
		
		System.out.println(dateString);
	}
}
