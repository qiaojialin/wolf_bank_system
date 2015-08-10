<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>&nbsp;<br><div align="center"> 
    <form action="invoice.invoice" method="post"> 
 	<p style="font-size: 19px;">贷款号　<input type="text" name="loankey"></p> 
 	<p style="font-size: 19px;">发放号　<input type="text" name="ddno"></p> 
 	<p style="font-size: 19px;">应收本金<input type="text" id="a1" name="v1" value='<%=session.getAttribute("a1") %>'></p> 
 	<p style="font-size: 19px;">应收利息<input type="text" id="a3" name="v3" value='<%=session.getAttribute("a2") %>'></p>		 
 	<p style="font-size: 19px;">贷款人<input type="text" id="a4" name="v4" value='<%=session.getAttribute("a3") %>'></p>	 
 	<p style="font-size: 19px;">发放日期<input type="text" id="a5" name="v5" value='<%=session.getAttribute("a4") %>'></p>	 
 	<p style="font-size: 19px;">到期日期<input type="text" id="a6" name="v6" value='<%=session.getAttribute("a5") %>'></p>
 		 
 	<input type="submit" value="查询"> 
	</form>				 
 
	</div>
  </body>
</html>
