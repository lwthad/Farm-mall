package com.farm.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.farm.pojo.TbAdmin;
import com.farm.service.LoginService;

/**
 * @author   tao 
 * @E-mail   ChinaPRNo1@163.com
 * @Time     2019年1月28日下午3:26:51
 * @version  1.0
 */
@Controller
public class LoginController {
	
	@Autowired 
	private LoginService loginService;
	
	@RequestMapping("/login")
	@ResponseBody
	public void Login(HttpServletRequest req,HttpServletResponse resp){
		
		//解决中文乱码
        try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

        //校验验证码
        /*String sessionCode = (String) req.getSession().getAttribute("session_vcode");
        String verifyCode = req.getParameter("verifyCode");
        if(!verifyCode.equalsIgnoreCase(sessionCode)){
            req.setAttribute("msg","验证码输入错误！");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
            //停止向下执行
            return;
        }*/

        //获取表单用户信息
        String adname =  req.getParameter("username");
        String adpasswd =  req.getParameter("password");
        TbAdmin admin = new TbAdmin(adname,adpasswd);

		//表单信息校验
        int i = loginService.login(admin);
        if(i > 0){
            req.getSession().setAttribute("session_username",adname);     //登陆成功，则将用户名存入session域,（如果同一浏览器未关闭登陆多个账号，会进行覆盖；不同浏览器属于不同会话，互相不影响）
            /*cookie值中间不能有空格*/
            Cookie cookie = new Cookie("uname", adname);//把用户名保存到cookies中，这样用户再次打开网站，login.jsp可以从Cookies取出用户名放在输入框
            cookie.setMaxAge(60*60*24);     //一天存活期
            resp.addCookie(cookie);
            /*重定向属于新的请求，url需要加项目名*/
            try {
				resp.sendRedirect(req.getContextPath()+"/result/success.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}  //重定向(url会改变)属于新的请求，如果上面用户名放到request域中，在目标页面请求不到
            return ;
        }else{
            req.setAttribute("msg","用户名不存在或密码错误");     //错误信息一般存到request域
            try {
				req.getRequestDispatcher("/result/error.jsp").forward(req,resp);
				//进行转发（url不变），转发不属于新的请求request
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}    
            return;
        }
	}
}
