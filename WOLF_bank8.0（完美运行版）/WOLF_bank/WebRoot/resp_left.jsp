<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="java.util.*,bean.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
	</head>

	<body background="2.jpg">

		<table width="100%" border="0" cellpadding="5" cellspacing="1"
			bgcolor="#ff9900">
			<tr bgcolor="#cccccc">
				<td height="10" align="center" valign="bottom" bgcolor="#cccccc">
					<span class="STYLE1">²Ëµ¥Ñ¡Ïî</span>
				</td>
			</tr>
			<%
				List list = (List) session.getAttribute("menulist");
				if (list != null) {
					Iterator<Menu> iter = list.iterator();
					while (iter.hasNext()) {
						Menu menu = iter.next();
			%>
			<tr bgcolor="#999999">
				<td height="5" align="center" bgcolor="#999999"  >
					
					<a style="color:#000000; font-size:16px; text-decoration:none" href="<%=basePath%><%=menu.getMenuUrl()%>" target="mainFrame"><%=menu.getMenuName()%></a>
						
					
				</td>
			</tr>

			<%
					}
				}
			%>

		</table>
	</body>
</html>
