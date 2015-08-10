<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
	</head>
	
	
<script language="javascript">
	function validate(f){
		
		
	}
</script>

	<body>
		<center>
<br/><br/>
<div align="center" >
	
	<form action="settlepay.spay" method="post" onSubmit="return validate(this)">
	<p style="font-size:19px">客&nbsp;户&nbsp;号<input id="city" type="text" name="client" /></p>  	
 	<p style="font-size:19px">银行账号<input id="address" type="text" name="acct" /></p>
 	<p style="font-size:19px">付款金额<input id="country" type="text" name="payamt" /></p>
 	<p style="font-size:19px">客户账号<input id="job" type="text" name="baseacctno" /></p>
	<input type="submit" value="提交">
	</form>
</div>
	<h4>${settlepay_info}</h4>
	</center>
	
	</body>
</html>