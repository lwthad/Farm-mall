package com.farm.service.imp;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.farm.mapper.TbAdminMapper;
import com.farm.pojo.TbAdmin;
import com.farm.pojo.TbAdminExample;
import com.farm.pojo.TbAdminExample.Criteria;
import com.farm.service.LoginService;

/**
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2019年1月28日下午4:03:58
 * @version  1.0
 */
public class LoginServiceImp implements LoginService {
	
	@Autowired
	private TbAdminMapper tbAdminExample;
	
	@Override
	public int login(TbAdmin admin) {
		admin.setAdname("admin");
		admin.setAdpassword("admin");
		
		TbAdminExample example = new TbAdminExample();
		Criteria cc = example.createCriteria();
		cc.andAdnameEqualTo(admin.getAdname());
		cc.andAdpasswordEqualTo(admin.getAdpassword());
		
		List<TbAdmin> list = tbAdminExample.selectByExample(example);
		for(TbAdmin i: list){
			System.err.println("admin"+i);
		}
		if(!list.isEmpty())
			return 1;
		
		return 0;
	}
	
	
}
