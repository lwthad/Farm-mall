package com.farm.service;
/**
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2018年10月29日下午2:04:44
 * @version  1.0
 */

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface PictureService {
	Map uploadPicture(MultipartFile uploadFile);
}
