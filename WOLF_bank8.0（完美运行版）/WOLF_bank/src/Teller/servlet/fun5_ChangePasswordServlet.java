package Teller.servlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import bean.*;
import Teller.dao.*;


public class fun5_ChangePasswordServlet extends HttpServlet {
	
public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
{
	    req.setCharacterEncoding("GBK");
		String userid = req.getParameter("userid");
		String olduserpass = req.getParameter("olduserpass");
		String userpass = req.getParameter("userpass");
		String changepassword_info; // 收集错误
		
		User user = new User();
		user.setUserid(userid);
		user.setPassword(olduserpass);
		if(new fun0_1_LoginCheckDao().getCheck(user)==1)
		{
			user.setPassword(userpass);
		try {
			if(new fun5_ChangePasswordDao().changePassword(user)==0) {
				changepassword_info="修改成功！";
				req.setAttribute("changepassword_info", changepassword_info);
			} else {
				changepassword_info="修改失败！";
				req.setAttribute("changepassword_info", changepassword_info);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		}
		else{
			changepassword_info="原始密码错误！";
			req.setAttribute("changepassword_info", changepassword_info);
		}
		req.getRequestDispatcher("fun5_changepassword.jsp").forward(req, resp);
	}
	

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}