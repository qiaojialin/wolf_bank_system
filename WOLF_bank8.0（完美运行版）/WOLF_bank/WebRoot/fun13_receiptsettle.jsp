<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>贷款回收结算表</title>
    <style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
    </style>
</head>
  
  <body  background="3.jpg" >
  <center>
  <br/><br/> 
	<div align="center" >
    <form action="receipt.receiptsettle" method="post">
	<p style="font-size:19px">客户账号 <input  type="text" name="v1"/></p>
	<p style="font-size:19px">客&nbsp;户&nbsp;号 <input  type="text" name="v2" /></p>
	<p style="font-size:19px">内部结算账号<input  type="text" name="v3" /></p>  
 	<p style="font-size:19px">付款金额&nbsp; <input  type="text" name="v4" /></p>
	<input type="submit" value="确定">
	</form>
	</div>
		<h4>${receiptsettle_info}</h4>
  </center>
  </body>
</html>