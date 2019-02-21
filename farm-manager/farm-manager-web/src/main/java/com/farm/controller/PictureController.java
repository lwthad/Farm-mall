package com.farm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.farm.common.utils.JsonUtils;
import com.farm.service.PictureService;

/**
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2018年10月29日下午2:52:40
 * @version  1.0
 */
@Controller
public class PictureController {
	@Autowired 
	PictureService pictureService;
	
	/**
	 * 上传图片
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping("/pic/upload")
	@ResponseBody
/*	public Map pictureupload(MultipartFile uploadFile){
		 Map resultM = pictureService.uploadPicture(uploadFile);
		 
		 return resultM;
	}*/
	public String pictureupload(MultipartFile uploadFile){
		Map resultM = pictureService.uploadPicture(uploadFile);
		
		//转为json数据
		String json = JsonUtils.objectToJson(resultM);
		return json;
	}
}
