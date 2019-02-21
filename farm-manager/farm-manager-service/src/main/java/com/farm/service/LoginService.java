package com.farm.service;

import com.farm.pojo.TbAdmin;

/**
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2019年1月28日下午4:03:27
 * @version  1.0
 */
public interface LoginService {
	
	/**
	 * 管理员登录
	 * @param admin
	 * @return
	 */
	int login(TbAdmin admin);
}
