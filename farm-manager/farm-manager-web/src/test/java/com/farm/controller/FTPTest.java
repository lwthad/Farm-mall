package com.farm.controller;
/**
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2018年10月28日下午10:20:18
 * @version  1.0
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.farm.common.utils.FtpUtil;

public class FTPTest {
	/**
	 * 测试ftp工具包——上传图片
	 * @throws FileNotFoundException
	 */
	@Test
	public void testFtpUtil() throws FileNotFoundException{
		File file = new File("D:\\upload\\121.jpg");
		FileInputStream fileInputStream = new FileInputStream(file);
		FtpUtil.uploadFile("192.168.111.161", 21, "ftpuser", "ftpuser", 
				"/home/ftpuser/www/images", "/2018/10/29", "ftpUtilTest2.jpg", fileInputStream);
	}
	
	/**
	 * 端口默认21
	 * 教材使用的ftp连接服务器方式,开始能连接上（很慢）但是上传图片大小为0k，
	 * 第二天直接连接不上 报错
	 * org.apache.commons.net.MalformedServerReplyException: Could not parse response code.
	 *	 Server Reply: SSH-2.0-OpenSSH_5.3
	 * @throws Exception
	 */
	@Test
	public void testFtpClient() throws Exception{
		//创建一个FtpClient对象
		FTPClient ftpClient = new FTPClient();
		//创建ftp连接，默认是21端口
		ftpClient.connect("192.168.111.161", 21);
		//登录ftp服务器，使用用户名和密码
		ftpClient.login("ftpuser", "ftpuser");
		//上传文件。
		//读取本地文件
		
		String str= "D:\\upload\\121.jpg";
		FileInputStream fileInputStream = new FileInputStream(new File(str));
		//设置上传的路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		//设置上传文件的类型为二进制类型
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		/*FTP协议有两种工作方式： 
		1.PORT方式  （主动）
		2.PASV方式  （被动）
		默认为PORT方式，修改为PASV方式*/
		ftpClient.enterLocalPassiveMode(); 
		//第一个参数：服务器端文档名
		//第二个参数：上传文档的inputStream
		ftpClient.storeFile("ftptest.jpg", fileInputStream);
		//关闭连接
		ftpClient.logout();
	}
	
	
	
	
	
	
	
	
	
	/************************SFTP*******************************/
	/**
	 * 端口默认22
	 * 网上档的，使用SFTP连接方式，可以成功上传图片 速度很快
	 * @throws Exception
	 */
	@Test
	public void testSFTPClient() throws Exception {
		FTPTest sf = new FTPTest(); 
		String host = "192.168.111.161";
		int port = 22;
		String username = "ftpuser";
		String password = "ftpuser";
		String directory = "/home/ftpuser/www/images";
		String uploadFile = "C:\\upload\\TimeTable.jpg";
		//String uploadFile = "‪C:\\Users\\tao\\Pictures\\JDKconfig\\121.jpg";
		
		//String downloadFile = "upload.txt";
		//String saveFile = "D:\\tmp\\download.txt";
		//String deleteFile = "delete.txt";
		ChannelSftp sftp=sf.connect(host, port, username, password);
		sf.upload(directory, uploadFile, sftp);
		//sf.download(directory, downloadFile, saveFile, sftp);
		//sf.delete(directory, deleteFile, sftp);
		//sftp.cd(directory);
		//sftp.mkdir("ss");
		System.out.println("finished");
	} 
	
	/**
	* 连接sftp服务器
	* @param host 主机
	* @param port 端口
	* @param username 用户名
	* @param password 密码
	* @return
	*/
	public ChannelSftp connect(String host, int port, String username,
	String password) {
		ChannelSftp sftp = null;
		try {
			JSch jsch = new JSch();
			jsch.getSession(username, host, port);
			Session sshSession = jsch.getSession(username, host, port);
			System.out.println("Session created.");
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			System.out.println("Session connected.");
			System.out.println("Opening Channel.");
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
			System.out.println("Connected to " + host + ".");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sftp;
	}
	
	/**
	* 上传文件
	* @param directory 上传的目录
	* @param uploadFile 要上传的文件
	* @param sftp
	 * @throws SftpException 
	 * @throws FileNotFoundException 
	*/
	public void upload(String directory, String uploadFile, ChannelSftp sftp) throws FileNotFoundException, SftpException {
		sftp.cd(directory);
		File file=new File(uploadFile);
		sftp.put(new FileInputStream(file), file.getName());
	}
	
	/**
	* 下载文件
	* @param directory 下载目录
	* @param downloadFile 下载的文件
	* @param saveFile 存在本地的路径
	* @param sftp
	 * @throws SftpException 
	 * @throws FileNotFoundException 
	*/
	public void download(String directory, String downloadFile,String saveFile, ChannelSftp sftp) throws SftpException, FileNotFoundException {
		sftp.cd(directory);
		File file=new File(saveFile);
		sftp.get(downloadFile, new FileOutputStream(file));
	}
	
}
