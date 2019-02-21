package com.farm.service.imp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.farm.common.utils.FtpUtil;
import com.farm.common.utils.IDUtils;
import com.farm.service.PictureService;

/**
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2018年10月29日下午2:07:25
 * @version  1.0
 */
@Service
public class PictureServiceImp implements PictureService {
	//自动注入常量
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	@Override
	public Map uploadPicture(MultipartFile uploadFile) {
		
		//实例化一个HashMap
		HashMap<Object, Object> resultMap = new HashMap<>();
		try {	
			//取原始文件名
			String originalFilename = uploadFile.getOriginalFilename();
			//生成一个新文件名
			String NewImageName = IDUtils.genImageName();
			//加文件后缀 方法：截取原始文件名的'.'以及后面的字符串
			NewImageName = NewImageName + 
					originalFilename.substring(originalFilename.lastIndexOf("."));
			//图片上传
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("/yyyy/MM/dd");  
			String dateString = formatter.format(date);
			//String imagePath = new DateTime().toString("/yyyy/MM/dd");
			String imagePath = dateString;
			boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, 
					FTP_PASSWORD, FTP_BASE_PATH, imagePath,
					NewImageName, uploadFile.getInputStream());
			if(!result){
				resultMap.put("error", 1);
				resultMap.put("messsge", "文件上传失败");
				return resultMap;
			}
			resultMap.put("error", 0);
			resultMap.put("url", IMAGE_BASE_URL + imagePath + "/" + NewImageName);
			return resultMap;
		} catch (Exception e) {
			resultMap.put("error", 1);
			resultMap.put("messsge", "文件上传发生异常");
			return resultMap;
		}
	}

}
