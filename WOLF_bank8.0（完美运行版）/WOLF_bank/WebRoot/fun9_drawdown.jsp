<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>贷款发放</title>
    <style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
    </style>
</head>
  
  <body  background="12.jpg" >
  <center>
  <br/><br/> 
	<div align="center" >
    <form action="drawdown.drawdown" method="post">
    <p style="font-size:19px">贷款号<input  type="text" name="v1" /></p>
	<p style="font-size:19px">发放号<input  type="text" name="v2"/></p>
	<p style="font-size:19px">贷款人<input  type="text" name="v3" /></p>
 	<p style="font-size:19px">发放金额 <input  type="text" name="v4" /></p>
	<p style="font-size:19px">客户结算默认账号<input  type="text" name="v5" /></p>  
 	
	<input type="submit" value="确定">&nbsp;
	</form>
	</div>
		<h4>${drawdown_info}</h4>
  </center>
  </body>
</html>