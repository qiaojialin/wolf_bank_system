package Teller.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import bean.*;
import Teller.dao.*;


public class fun4_RegisterServlet extends HttpServlet {
	
public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
{
		req.setCharacterEncoding("GBK");
		String userid = req.getParameter("userid");
		String username = req.getParameter("username");
		String usersex = req.getParameter("usersex");
		String userage = req.getParameter("userage");
		String userphone = req.getParameter("userphone");
		String useridentity = req.getParameter("useridentity");
		String roleid = req.getParameter("roleid");
		String userpass = req.getParameter("userpass");
		String register_info; // ÊÕ¼¯´íÎó
		
		User user = new User();
		user.setUserid(userid);
		user.setPassword(userpass);
		user.setAge(userage);
		user.setName(username);
		user.setPhone(userphone);
		user.setRoleid(roleid);
		user.setSex(usersex);
		user.setidentity(useridentity);
		
		try {
			if(new fun4_RegisterDao().register(user)==0) {
				register_info="×¢²á³É¹¦£¡";
				req.setAttribute("register_info", register_info);
			} else {
				register_info="×¢²áÊ§°Ü£¡";
				req.setAttribute("register_info", register_info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		req.getRequestDispatcher("fun4_register.jsp").forward(req, resp);
		
	}
	

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}