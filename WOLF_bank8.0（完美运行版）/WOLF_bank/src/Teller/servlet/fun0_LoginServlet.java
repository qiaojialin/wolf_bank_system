package Teller.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bean.*;
import Teller.dao.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class fun0_LoginServlet extends HttpServlet {
	
public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
{
		req.setCharacterEncoding("GBK");
		String userid = req.getParameter("userid");
		String userpass = req.getParameter("userpass");
		String info; // 收集错误
		// User user = new User("200","123");
		User user = new User();
		user.setUserid(userid);
		user.setPassword(userpass);
		Login login=new Login();
		login.setUserId(userid);
		//获取系统时间
		Date d=new Date();
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd/hh:mm:ss");
		login.setLoginTime(df.format(d));
		//获取客户端IP
		String ip = req.getHeader("x-forwarded-for"); 
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		ip = req.getHeader("Proxy-Client-IP"); 
		} 
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		ip = req.getHeader("WL-Proxy-Client-IP"); 
		} 
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		ip = req.getRemoteAddr(); 
		} 
		login.setLoginIp(ip);
		try {
			if (new fun0_1_LoginCheckDao().getCheck(user) != 1) {
				info = "用户登陆失败，错误的用户名和密码！";
				req.setAttribute("info", info);
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			} else {
				if(new fun0_3_LoginHistoryDao().loginHistory(login)!=0);
				List menulist = new fun0_2_MenuCheckDao().findMenu(user);
				if(menulist!=null&&menulist.size()!=0){	
					HttpSession ses = req.getSession();
					ses.setAttribute("welcom", userid);
					ses.setAttribute("menulist", menulist);
					req.getRequestDispatcher("resp.jsp").forward(req, resp);
				}else{
					info = "用户权限不足";
					req.setAttribute("info", info);
					req.getRequestDispatcher("index.jsp").forward(req, resp);
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
			
}
	

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}