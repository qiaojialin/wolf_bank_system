<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<html>
	<head>
		<base href="<%=basePath%>">
	</head>
	<frameset rows="8%,92%">
	<frame src="resp_head.jsp"/>
	<frameset cols="100,*" frameborder="no" border="2" framespacing="0">
		<frame src="resp_left.jsp" name="leftFrame" noresize="noresize"
			marginwidth="0" marginheight="0" id="leftFrame" title="leftFrame"
			style="border: medium double rgb(250, 0, 255)" />
		<frame src="resp_main.jsp" name="mainFrame" marginwidth="0"
			marginheight="0" id="mainFrame" title="mainFrame" />
	</frameset>
	</frameset>
	
	
</html>
